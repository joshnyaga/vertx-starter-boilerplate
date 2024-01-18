package com.joshua.starter.starter_boilerplate;

import com.joshua.starter.starter_boilerplate.config.ConfigLoader;
import com.joshua.starter.starter_boilerplate.routing.RestApiTest;
import com.joshua.starter.starter_boilerplate.util.Constants;
import io.vertx.rxjava3.core.AbstractVerticle;
import io.vertx.rxjava3.ext.web.Router;
import io.vertx.rxjava3.ext.web.handler.CorsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestApiVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(RestApiVerticle.class);
  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);
    router.route().handler(CorsHandler
      .create("*")
      .allowedHeaders(Constants.allowedHeaders())
      .allowedMethods(Constants.getAllowedMethods()));

    RestApiTest.attach(router);
    ConfigLoader.load(vertx)
      .flatMap(
        config-> vertx.createHttpServer().requestHandler(router).exceptionHandler(err->{
          LOG.error("Http server error occurred: ", err);
        }).rxListen(config.getApplicationConfig().getServerPort())
      ).doOnError(error->{
        LOG.error("Failed to load configs", error);
      }).doOnError(err ->LOG.debug("Error occurred"))
      .doOnSuccess(s->LOG.info("deployed verticle"))
      .subscribe();
  }
}
