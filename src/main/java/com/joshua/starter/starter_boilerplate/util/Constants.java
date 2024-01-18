package com.joshua.starter.starter_boilerplate.util;

import io.vertx.core.http.HttpMethod;

import java.util.HashSet;
import java.util.Set;

public class Constants {
  public static Set<String> allowedHeaders(){
    Set<String> allHeaders = new HashSet<>();
    allHeaders.add("x-requested-with");
    allHeaders.add("Access-Control-Allow-Origin");
    allHeaders.add("origin");
    allHeaders.add("Content-Type");
    allHeaders.add("accept");
    allHeaders.add("Authorization");

    return allHeaders;

  }
  public static Set<HttpMethod> getAllowedMethods () {
    Set<HttpMethod> allowedMethods = new HashSet<>();
    allowedMethods.add(HttpMethod.GET);
    allowedMethods.add(HttpMethod.POST);
    allowedMethods.add(HttpMethod.OPTIONS);
    allowedMethods.add(HttpMethod.PUT);
    return  allowedMethods;
  }
}
