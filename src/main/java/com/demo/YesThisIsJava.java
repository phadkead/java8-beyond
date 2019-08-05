package com.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class YesThisIsJava {

  // static collection methods
  private static final List<String> myList = List.of("I", "Like", "this", "syntax");

  // new way to define set
  private static final Set<String> mySet = Set.of("no", "duplicates");

  //new way to define map
  private static final Map<String, Integer> myMap =
      Map.of("A", 1, "B", 2, "C", 3);


  public static void main(String[] args) throws IOException, InterruptedException {

    //var
    var something = "myValue";

    // But can't use as methods arguments yet

    //don't do this:
    List<String> list = new ArrayList<>();
    var users = new ArrayList<String>();  //inferred as ArrayList and not List

    var id = 0;// At this moment, compiler interprets variable id as integer.
    //id = "34"; // This will result in compilation error: This is Not Javascript

    //great for storing intermediate results
    for (var item : myList) {
      var appended = item + "random";
      System.out.println(appended);
    }

    // For consistency, Java 11 allows var in lambdas
    Calculator divide = (x,  y) -> x / y;
    System.out.println("Lambda Division:\t" + divide.doMath(25, 5));

    //unmodifiable list
    var filtered = myList.stream().filter(item -> item.startsWith("I"))
        .collect(Collectors.toUnmodifiableList());

    // Standardized new httpclient tp replace old HttpURLConnection from Java 11

    var httpClient = HttpClient.newHttpClient();

    var request = HttpRequest.newBuilder().GET()
        .uri(URI.create("https://www.google.com.au/search?q=java")).build();

    var response = httpClient.send(request, BodyHandlers.ofString());

    //pattern matching in java 12
    var patternMatching = whatIsToday(DAY.SATURDAY);
    System.out.println(patternMatching);

    //raw string literals, not scheduled to be releasedyet
    String myNewContent = `<html>
                          <body>
                             <p>Hello World.</p>
                          </body>
                      </html>
                      `;

  }

  // A step towards pattern matching
  private static String whatIsToday(DAY day) {
    var today = "";
    switch (day) {
      //syntax is similar to lambdas, no more BREAK
      case SATURDAY, SUNDAY -> today = "Weekend day";
      case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> today = "Working day";
      default -> throw new IllegalArgumentException("Invalid day: " + day.name());
    }
    return today;
  }

}


@FunctionalInterface
interface Calculator {

  double doMath(double x, double y);

}

