package com.zemoga.challenge.infrastructure.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.entities.TwitterMedia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioDTO {
  private Long id = null;
  private String experience;
  @JsonProperty("image_path")
  private String imagePath;
  @NotBlank
  private String name;
  @NotBlank
  @JsonProperty("twitter_user")
  private String twitterUser;
  private String email;
  private String address;
  private String phone;
  @JsonProperty("zip_code")
  private String zipCode;

  public PortfolioDTO(Portfolio portfolio) {
    this.id = portfolio.getId();
    this.experience = portfolio.getExperience();
    this.imagePath = portfolio.getImagePath();
    this.name = portfolio.getName();
    this.twitterUser = portfolio.getTwitterUser();
    this.email = portfolio.getEmail();
    this.address = portfolio.getAddress();
    this.phone = portfolio.getPhone();
    this.zipCode = portfolio.getZipCode();
  }

  public Portfolio toPortfolio() {
    return Portfolio.builder()
            .id(this.id)
            .address(this.address)
            .email(this.email)
            .experience(this.experience)
            .imagePath(this.imagePath)
            .name(this.name)
            .phone(this.phone)
            .twitterUser(this.twitterUser)
            .zipCode(this.zipCode)
            .build();
  }
}
