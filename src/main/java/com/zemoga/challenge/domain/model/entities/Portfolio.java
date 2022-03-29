package com.zemoga.challenge.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {
  private Long id;
  private String experience;
  private String imagePath;
  private String name;
  private String twitterUser;
  private String email;
  private String address;
  private String phone;
  private String zipCode;
}
