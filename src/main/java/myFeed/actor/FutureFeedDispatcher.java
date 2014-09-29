package myFeed.actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import myFeed.domain.FeedEntry;
import myFeed.service.FeedDispatcher;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.dispatch.OnComplete;
import akka.util.Timeout;

public class FutureFeedDispatcher implements FeedDispatcher {
	
	private ActorSystem as;
	
	public FutureFeedDispatcher() {
		as = ActorSystem.create("feed");
	}

	public void add(List<Long> friendIds,final FeedEntry entry) throws Exception {
		long begin = System.currentTimeMillis();
		String result = null;
		Timeout timeout = new Timeout(Duration.create(5, "seconds"));
		
		List<Future<String>> fl = new ArrayList<Future<String>>(5);
		for (int i = 0 ; i < 5 ; i++) {
			final Map<String,Object> map = new HashMap<String,Object>();
			map.put("index", new Integer(i));
			map.put("entry", entry);
			Future f = Futures.future(new Callable<String>() {
				public String call() throws Exception {
					System.out.println(entry.getTitle()+":"+ "call addFeed " + entry.getContent() +" loop" + map.get("index") + " and sleep 1s");
					Thread.sleep(50000);
					return "success" + map.get("index");
				}
			}, as.dispatcher());
			fl.add(f);
		}
		
		Future<Iterable<String>> cf = Futures.sequence(fl, as.dispatcher());
		Future<String> sf = cf.map(new Mapper<Iterable<String>, String>() {

			@Override
			public String apply(Iterable<String> parameter) {
				for(String s : parameter) {
					System.out.println(s);
				}
				
				return "success";
			}
			
		}, as.dispatcher());
		
		//result = Await.result(sf, timeout.duration());
		long end = System.currentTimeMillis();
		System.out.println("addFeed return "+ result +" use:" + (end - begin) + "ms");

	}

	public void remove(List<Long> friendIds, FeedEntry entry) throws Exception {
		// TODO Auto-generated method stub

	}

}
