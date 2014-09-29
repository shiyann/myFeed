package myFeed.actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import akka.util.Timeout;
import myFeed.domain.FeedEntry;
import myFeed.service.FeedDispatcher;

public class ActorFeedDispatcher implements FeedDispatcher {
	
	private ActorSystem as;
	
	private ActorRef router;
	
	public ActorFeedDispatcher() {
		as = ActorSystem.create("feed");
		router = as.actorOf(new RoundRobinPool(5).props(Props.create(FeedActor.class)),"FeedRouter");
	};

	public void add(List<Long> friendIds, FeedEntry entry) throws Exception {
		long begin = System.currentTimeMillis();
		String result = null;
		Timeout timeout = new Timeout(Duration.create(60, "seconds"));
		
		List<Future<String>> fl = new ArrayList<Future<String>>(5);
		for (int i = 0 ; i < 5 ; i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("index", new Integer(i));
			map.put("entry", entry);
			Future f = Patterns.ask(router, map, 2000000);
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
		
		result = Await.result(sf, timeout.duration());
		long end = System.currentTimeMillis();
		System.out.println("addFeed return "+ result +" use:" + (end - begin) + "ms");
	}

	public void remove(List<Long> friendIds, FeedEntry entry) throws Exception {
		// TODO Auto-generated method stub

	}

}
