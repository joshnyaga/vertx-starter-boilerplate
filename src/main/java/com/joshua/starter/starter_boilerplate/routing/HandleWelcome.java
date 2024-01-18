package com.joshua.starter.starter_boilerplate.routing;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleWelcome implements Handler<RoutingContext> {
  private static final Logger LOG = LoggerFactory.getLogger(HandleWelcome.class);
  @Override
  public void handle(RoutingContext ctx) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.put("name", "vertx-test");
    jsonObject.put("created-by", "Joshua");

    LOG.info("Path {} responds with {}", ctx.normalizedPath(), jsonObject);
    ctx.response()
      .putHeader("","")
      .end(jsonObject.encode());
  }
}
