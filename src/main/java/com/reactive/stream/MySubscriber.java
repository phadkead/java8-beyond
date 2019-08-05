package com.reactive.stream;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MySubscriber implements Subscriber<Integer> {

  private Subscription subscription;

  @Override
  public void onSubscribe(Subscription subscription) {
    this.subscription = subscription;
    //subscriber is ready to accept one item
    subscription.request(1);
  }

  @Override
  public void onNext(Integer item) {
    System.out.println(item);
    subscription.request(3);  // used to handle back pressure
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
