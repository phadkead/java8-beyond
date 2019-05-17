package twitter.stream;

import twitter4j.StreamListener;
import twitter4j.TwitterStream;

public class TwitterStreamSubscriber {

  public TwitterStreamSubscriber() {

  }

  public void readStream() {
    TwitterStream a = TwitterStreamBuilder.getTwitterStream().addListener(new StreamListener() {
      public void onException(Exception e) {
        System.out.println("exception" + e.getMessage());
      }
    });


  }


}
