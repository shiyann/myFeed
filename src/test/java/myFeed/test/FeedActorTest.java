package myFeed.test;

import myFeed.domain.FeedEntry;
import myFeed.service.FeedServiceImpl;
import myFeed.service.FeedService;

public class FeedActorTest {

	public static void main(String[] args) {
		try {
			final FeedService fs = new FeedServiceImpl();
			Thread t1 = new Thread(){

				@Override
				public void run() {
					try {
						for (int i = 0 ; i < 10 ; i++) {
							FeedEntry fe = new FeedEntry();
							fe.setTitle(this.getName());
							fe.setContent(String.valueOf(i));
							fs.addFeed(fe);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			};
			t1.start();
			
			//Thread.sleep(100);
			
			Thread t2 = new Thread(){

				@Override
				public void run() {
					try {
						for (int i = 0 ; i < 10 ; i++) {
							FeedEntry fe = new FeedEntry();
							fe.setTitle(this.getName());
							fe.setContent(String.valueOf(i));
							fs.addFeed(fe);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			};
			t2.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//System.exit(0);
		}
	}
}
