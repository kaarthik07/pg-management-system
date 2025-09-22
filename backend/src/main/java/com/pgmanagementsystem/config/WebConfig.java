package com.pgmanagementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${cors.allowed-origins:http://localhost:5173,http://localhost:3000}")
  private String allowedOrigins;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    String[] patterns = allowedOrigins.replace(" ", "").split(",");
    registry.addMapping("/api/**")
        .allowedOriginPatterns(patterns)
        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
        .allowCredentials(true);
  }
}
