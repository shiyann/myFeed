package myFeed.service;

import java.util.List;

import myFeed.domain.FeedEntry;

public interface FeedDispatcher {
	
	public void add(List<Long> friendIds, FeedEntry entry) throws Exception;
	
	public void remove(List<Long> friendIds, FeedEntry entry) throws Exception;

}
