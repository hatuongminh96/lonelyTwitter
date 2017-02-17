package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tuongmin on 2/16/17.
 */

public class TweetList {
    private ArrayList<NormalTweet> tweets = new ArrayList<NormalTweet>();

    public void add(NormalTweet tweet) {
        if (this.hasTweet(tweet)) {
            throw new IllegalArgumentException();
        }
        tweets.add(tweet);
    }

    public  boolean hasTweet(Tweet tweet) {
        for (Tweet t: tweets) {
            if (t.equals(tweet)) return true;
        }
        return false;
    }

    public NormalTweet getTweet(int index) {
        return tweets.get(index);
    }

    public ArrayList<NormalTweet> getTweets() {
        ArrayList<NormalTweet> result = tweets;
        Collections.sort(result);
        return result;
    }

    public int getCount(){
        return tweets.size();
    }

    public void delete(NormalTweet tweet) {
        tweets.remove(tweet);
    }
}
