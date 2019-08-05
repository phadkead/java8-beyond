package com.reactive.stream;

import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class SimplePublisher {

  public static void main(String[] args) throws InterruptedException {

    MySubscriber subscriber = new MySubscriber();

    SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
    publisher.subscribe(subscriber);

    IntStream.range(1, 10).forEach(publisher::submit);

    Thread.sleep(10000);
    publisher.close();
  }
}
