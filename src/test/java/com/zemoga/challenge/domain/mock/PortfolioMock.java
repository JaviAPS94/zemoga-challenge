package com.zemoga.challenge.domain.mock;

import com.zemoga.challenge.domain.model.entities.Portfolio;

import java.util.Optional;

public class PortfolioMock {
  public static Portfolio create() {
    return Portfolio.builder()
            .id(1L)
            .twitterUser("twitterUser")
            .phone("09956398751")
            .zipCode("5893")
            .imagePath("http://www.zemoga.com/images/user/1/profile.jpg")
            .email("test@test.com")
            .name("test name")
            .address("test address")
            .experience("test experience")
            .build();
  }

  public static Optional<Portfolio> createOptional() {
    return Optional.of(Portfolio.builder()
            .id(1L)
            .twitterUser("twitterUser")
            .phone("09956398751")
            .zipCode("5893")
            .imagePath("https://www.zemoga.com/images/user/1/profile.jpg")
            .email("test@test.com")
            .name("test name")
            .address("test address")
            .experience("test experience")
            .build()
    );
  }
}
