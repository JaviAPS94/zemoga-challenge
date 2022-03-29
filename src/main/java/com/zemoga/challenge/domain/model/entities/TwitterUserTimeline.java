package com.zemoga.challenge.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TwitterUserTimeline {
  private String createdAt;
  private Long id;
  private String text;
  private TwitterEntity entities;
}
