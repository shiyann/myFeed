package myFeed.actor;

import java.util.Map;

import myFeed.domain.FeedEntry;
import akka.actor.UntypedActor;

public class FeedActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		
		if (message instanceof Map) {
			Map<String,Object> map = (Map<String,Object>)message;
			FeedEntry fe = (FeedEntry)map.get("entry");
			System.out.println(fe.getTitle()+":"+ "call addFeed " + fe.getContent() +" loop" + map.get("index") + " and sleep 1s");
			Thread.sleep(5000);
			
			//System.out.println(fe.getTitle() +":"+ "feed add"+ fe.getContent());
			getSender().tell("success" + map.get("index"), getSender());
		} else {
			unhandled(message);
		}
	}

}
