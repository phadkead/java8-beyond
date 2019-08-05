### Exploring Java 9 Flow API continued
This is an attempt to use Java's new Flow API to a real time use case. 

We are using twitter4j library to keep listening to twitter stream in real time. 
twitter4j provides TwitterStreamFactory, you can plug-in a listener to it. The rate at which tweets are published can be very fast. To keep up with that, we have plugged our Subscriber with that.  
For a publisher, we will use `SubmissionPublisher` provided by Java

Like Standard reactive programming, it contains three channels: 
Data, Error. Complete

