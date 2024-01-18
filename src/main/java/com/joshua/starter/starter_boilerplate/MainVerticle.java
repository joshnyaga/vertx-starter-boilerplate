package com.joshua.starter.starter_boilerplate;



import com.joshua.starter.starter_boilerplate.config.ConfigLoader;
import com.joshua.starter.starter_boilerplate.routing.RestApiTest;
import com.joshua.starter.starter_boilerplate.util.Constants;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.rxjava3.core.AbstractVerticle;
import io.vertx.rxjava3.core.http.HttpServer;
import io.vertx.rxjava3.core.Vertx;
import io.vertx.rxjava3.ext.web.Router;
import io.vertx.rxjava3.ext.web.RoutingContext;
import io.vertx.rxjava3.ext.web.client.HttpResponse;
import io.vertx.rxjava3.ext.web.client.WebClient;
import io.vertx.rxjava3.ext.web.client.predicate.ResponsePredicate;
import io.vertx.rxjava3.ext.web.handler.CorsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
  // the logger - uses the logback.xml file
  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);
  private WebClient webClient;
  //the main method
  public static void main(String[] args) {
    var vertx = Vertx.vertx();

    //add exceptions handler
    vertx.exceptionHandler(error ->{
      LOG.error("Unhandled: ", error);
    });


    //deploy the main verticle
    vertx.rxDeployVerticle(new MainVerticle()).subscribe(
        s->LOG.info("Deployed"),
      e->LOG.error("An error occurred", e)
    );

  }

  @Override
  public void start() throws Exception {
    //deploy the rest api verticle
    //we are deploying this verticle on many threads to maximize resource utilization
    vertx.rxDeployVerticle(RestApiVerticle.class.getName(),
      new DeploymentOptions().setInstances(processors())
      ).subscribe(
        s-> LOG.info("Deployed {} with id {}", RestApiVerticle.class.getName(), s),
        e->LOG.error("Error deploying verticle", e)
    );
  }

  //get available processors
  private static int processors() {
    //make sure at least 1 verticle is deployed
    return Math.max(1,Runtime.getRuntime().availableProcessors());
  }


}
