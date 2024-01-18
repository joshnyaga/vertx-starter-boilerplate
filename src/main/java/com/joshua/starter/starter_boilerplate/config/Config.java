package com.joshua.starter.starter_boilerplate.config;


import io.vertx.core.json.JsonObject;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Builder
@Value
@ToString
public class Config {
  //definition of class variables -configurations

  DbConfig dbConfig;
  ApplicationConfig applicationConfig;

  //method from
  //receives a JSONObject with the configurations and builds the config class
  public static Config from(JsonObject config){

    return Config.builder()
      .dbConfig(
        DbConfig.builder()
          .host(config.getString(ConfigLoader.DB_HOST))
          .port(config.getInteger(ConfigLoader.DB_PORT))
          .database(config.getString(ConfigLoader.DB_DATABASE))
          .user(config.getString(ConfigLoader.DB_USER))
          .password(config.getString(ConfigLoader.DB_PASSWORD))
          .build()
      )
      .applicationConfig(
        ApplicationConfig.builder()
          .serverPort(config.getInteger(ConfigLoader.SERVER_PORT))
          .version(config.getInteger(ConfigLoader.VERSION))
          .build()
      )
      .build();
  }

}
