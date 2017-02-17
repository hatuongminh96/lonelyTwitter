package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

/**
 * Created by tuongmin on 2/16/17.
 */

public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    public void testAddTweet() {
        boolean thr = false;
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");
        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));
        try {
            tweets.add(tweet);
        }
        catch (IllegalArgumentException e) {
            thr = true;
        }
        assertTrue(thr);
    }

    public void testHasTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");
        NormalTweet t2 = tweet;
        assertFalse(tweets.hasTweet(tweet));
        tweets.add(tweet);
        assertEquals(tweets.hasTweet(t2),t2.equals(tweet));
    }

    public void testGetTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");
        tweets.add(tweet);
        NormalTweet returnedTweet = tweets.getTweet(0);
        assertEquals(tweet.getMessage(), returnedTweet.getMessage());
        assertEquals(tweet.getDate(), returnedTweet.getDate());
    }

    public void testDeleteTweet() {
        TweetList tweets = new TweetList();
        NormalTweet tweet = new NormalTweet("some tweet");
        tweets.add(tweet);
        tweets.delete(tweet);
        assertFalse(tweets.hasTweet(tweet));
    }

    public void testGetTweets() {
        TweetList tweets = new TweetList();
        NormalTweet t2 = new NormalTweet("some tweet");
        NormalTweet t1 = new NormalTweet("some tweet");
        t1.setDate(new Date());
        t2.setDate(new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L));
        TweetList res = new TweetList();
        res.add(t1);
        res.add(t2);
        tweets.add(t2);
        tweets.add(t1);
        assertEquals(res.getTweets(), tweets.getTweets());
    }

    public void testGetCount() {
        TweetList tweets = new TweetList();
        NormalTweet t2 = new NormalTweet("some tweet");
        tweets.add(t2);
        assertEquals(tweets.getCount(), 1);
    }
}
