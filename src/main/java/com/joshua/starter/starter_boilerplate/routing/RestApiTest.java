package com.joshua.starter.starter_boilerplate.routing;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.ext.web.Router;
import io.vertx.rxjava3.ext.web.RoutingContext;

public class RestApiTest {
  public static void attach(Router parent){
      parent.get("/").handler(new HandleWelcome());

  }


}
