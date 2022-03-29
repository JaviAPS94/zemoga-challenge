package com.zemoga.challenge.infrastructure.http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.entities.TwitterUserTimeline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterUserTimelineDTO {
  @JsonProperty("created_at")
  private String createdAt;
  private Long id;
  private String text;
  private EntityDTO entities;

  public TwitterUserTimelineDTO(TwitterUserTimeline twitterUserTimeline) {
    this.id = twitterUserTimeline.getId();
    this.text = twitterUserTimeline.getText();
    this.createdAt = twitterUserTimeline.getCreatedAt();
    this.entities = new EntityDTO(twitterUserTimeline.getEntities());
  }

  public TwitterUserTimeline toTwitterUserTimeline() {
    return TwitterUserTimeline.builder()
            .createdAt(createdAt)
            .id(id)
            .text(text)
            .entities(entities.toTwitterEntity())
            .build();
  }
}
