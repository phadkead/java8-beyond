package com.reactive.twitter;

import java.io.FileNotFoundException;
import java.util.concurrent.SubmissionPublisher;

public class Main {

  public static void main(String[] args)   {
    SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

    publisher.subscribe(new TwitterStreamSubscriber().getSubscriber());


  }
}
