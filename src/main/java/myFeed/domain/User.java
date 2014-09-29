package myFeed.domain;

import java.util.List;
import java.util.Set;

public class User {
	
	private long userId;
	
	private String userName;
	
	private Set<User> friends;
	
	private List<FeedEntry> feedList;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public List<FeedEntry> getFeedList() {
		return feedList;
	}

	public void setFeedList(List<FeedEntry> feedList) {
		this.feedList = feedList;
	}

}
