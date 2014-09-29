package myFeed.service;

import java.util.ArrayList;
import java.util.List;

import myFeed.actor.ActorFeedDispatcher;
import myFeed.actor.FutureFeedDispatcher;
import myFeed.domain.FeedEntry;

public class FeedServiceImpl implements FeedService {
	
	private FeedDispatcher feedDispatcher;
	
	public FeedServiceImpl() {
		feedDispatcher = new ActorFeedDispatcher();
		//feedDispatcher = new FutureFeedDispatcher();
	}

	public void addFeed(FeedEntry feedEntry) throws Exception {
		List<Long> friendIdList = new ArrayList<Long>();
		feedDispatcher.add(friendIdList, feedEntry);
	}

	public void updateFeed(FeedEntry feedEntry) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void deleteFeed(long feedEntryId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<FeedEntry> findMyfeedList(long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
