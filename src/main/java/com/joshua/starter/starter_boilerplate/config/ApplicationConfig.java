package com.joshua.starter.starter_boilerplate.config;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApplicationConfig {
  int version;
  int serverPort;

//for logging purpose
  @Override
  public String toString() {
    return "ApplicationConfig{" +
      "version=" + version +
      ", serverPort=" + serverPort +
      '}';
  }
}
