package com.example.CRUDEMPLOYEES.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.example.CRUDEMPLOYEES.constants.ApiDocConstants.*;

@Configuration
public class SwaggerConfig {

  @Bean
public OpenAPI customOpenApi(){
return new OpenAPI()
    .info(new Info()
        .title(API_TITLE)
        .version(API_VERSION)
        .description(API_DESCRIPTION));
  }
}
