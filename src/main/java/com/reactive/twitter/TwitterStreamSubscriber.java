package com.reactive.twitter;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStreamFactory;


class TwitterStreamSubscriber {

  Subscriber<String> getSubscriber() {

    MyTwitterSubscriber subscriber = new MyTwitterSubscriber(); //don't call me, I will call you

    new TwitterStreamFactory().getInstance().addListener(
        new StatusAdapter() {
          public void onStatus(Status status) {
            subscriber.onNext(status.getText());
          }

          public void onException(Exception ex) {
            subscriber.onError(ex);
          }
        }
    )
        .filter(getFilterQuery());
    return subscriber;
  }

  class MyTwitterSubscriber implements Subscriber<String> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
      this.subscription = subscription;  // can be used to signal more data or cancel
      //nothing will be send until you request for data
      subscription.request(1);
    }

    @Override
    public void onNext(String item) {
      System.out.println(item);
      subscription.request(3); //3 invocations of onNext
    }

    @Override
    public void onError(Throwable throwable) {
      throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
      System.out.println("done");
    }
  }

  private static FilterQuery getFilterQuery() {
    FilterQuery filterQuery = new FilterQuery();
    filterQuery.language("en");
    filterQuery.track("#MondayMotivation");
    return filterQuery;
  }
}
