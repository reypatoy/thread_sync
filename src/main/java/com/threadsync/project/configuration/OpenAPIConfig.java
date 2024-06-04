package com.threadsync.project.configuration;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OpenAPIConfig {

  @Value("${openapi.url}")
  public String url;

  @Bean
  public OpenAPI myOpenAPI() {
      Server server = new Server();
      server.setUrl(url);
      server.setDescription("Server URL");
    return new OpenAPI()
        .servers(List.of(server));
  }
}