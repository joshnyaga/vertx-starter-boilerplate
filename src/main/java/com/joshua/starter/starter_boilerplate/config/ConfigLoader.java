package com.joshua.starter.starter_boilerplate.config;


import io.reactivex.rxjava3.core.Single;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.config.ConfigRetriever;
import io.vertx.rxjava3.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {

  public static String SERVER_PORT ="SERVER_PORT";
  public static String VERSION ="VERSION";
  public static final String DB_HOST = "DB_HOST";
  public static final String DB_PORT = "DB_PORT";
  public static final String DB_DATABASE = "DB_DATABASE";
  public static final String DB_USER = "DB_USER";
  public static final String DB_PASSWORD = "DB_PASSWORD";

  //we want to load our configurations from application.yml file
  public static final String APPLICATION_YML = "application.yml";


  //method to load the configurations
  //returns a vertx future of object config
  //receives a vertx instance
  public static Single<Config> load(Vertx vertx){
    //create config options from which we load from
    var yamlStore = new ConfigStoreOptions()
      .setType("file")
      .setFormat("yaml")
      .setConfig(new JsonObject().put("path", APPLICATION_YML));

    //we can add another config from db


    //add the config to a store using configRetrieve
    var retriver = ConfigRetriever.create(vertx, new ConfigRetrieverOptions()
      .addStore(yamlStore)
    );

    //map to the config using the from method in config class
    return retriver.getConfig().map(Config::from);

  }

}
