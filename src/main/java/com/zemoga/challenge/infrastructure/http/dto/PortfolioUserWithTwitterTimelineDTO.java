package com.zemoga.challenge.infrastructure.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioUserWithTwitterTimelineDTO {
  private PortfolioDTO portfolio;
  private List<TwitterUserTimelineDTO> tweets;
}
