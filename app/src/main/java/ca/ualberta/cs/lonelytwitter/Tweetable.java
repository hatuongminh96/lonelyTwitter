package ca.ualberta.cs.lonelytwitter;

/**
 * Created by tuongmin on 1/19/17.
 */

public interface Tweetable {
    public String getMessage();
    public void setMessage(String message) throws TweetTooLongException;
}
