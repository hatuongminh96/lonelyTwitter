package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by tuongmin on 1/19/17.
 * This is the abstract base class Tweet
 * It includes the constructor, override toString, getter and setter for date and messages
 */

public abstract class Tweet implements Tweetable{
    private Date date;
    private String message;

    public Tweet(String message){
        this.message = message;
        this.date = new Date();
    }

    public Tweet(Date date, String message){
        this.message = message;
        this.date = date;
    }

    /**
     * Combine date and message as a String with | delimiter
     * @return combined string
     */
    @Override
    public String toString(){
        return date.toString() + " | " + message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) throws TweetTooLongException{
        if (message.length() > 140){
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    public abstract Boolean isImportant();
}
