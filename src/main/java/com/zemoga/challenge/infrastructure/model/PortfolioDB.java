package com.zemoga.challenge.infrastructure.model;

import com.zemoga.challenge.domain.model.entities.Portfolio;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio_ap")
@Data
@NoArgsConstructor
public class PortfolioDB {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @Column(name = "experience")
  private String experience;

  @Column(name = "image_path")
  private String imagePath;

  @Column(name = "name")
  private String name;

  @Column(name = "twitter_user")
  private String twitterUser;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private String phone;

  @Column(name = "zip_code")
  private String zipCode;

  public PortfolioDB(Portfolio portfolio) {
    this.experience = portfolio.getExperience();
    this.imagePath = portfolio.getImagePath();
    this.name = portfolio.getName();
    this.address = portfolio.getAddress();
    this.email = portfolio.getEmail();
    this.phone = portfolio.getPhone();
    this.twitterUser = portfolio.getTwitterUser();
    this.zipCode = portfolio.getZipCode();
  }

  public Portfolio toDomainEntity() {
    return Portfolio.builder()
            .id(id)
            .address(address)
            .email(email)
            .experience(experience)
            .imagePath(imagePath)
            .name(name)
            .phone(phone)
            .twitterUser(twitterUser)
            .zipCode(zipCode)
            .build();
  }
}
