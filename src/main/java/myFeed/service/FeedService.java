package myFeed.service;

import java.util.List;

import myFeed.domain.FeedEntry;

public interface FeedService {
	
	public void addFeed(FeedEntry feedEntry) throws Exception;
	
	public void updateFeed(FeedEntry feedEntry) throws Exception;
	
	public void deleteFeed(long feedEntryId) throws Exception;
	
	public List<FeedEntry> findMyfeedList(long userId) throws Exception;

}
