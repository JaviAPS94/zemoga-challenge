package com.zemoga.challenge.infrastructure.http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zemoga.challenge.domain.model.entities.TwitterEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityDTO {
  private List<MediaDTO> media;

  public EntityDTO(TwitterEntity twitterEntity) {
    this.media = twitterEntity.getMedia().stream().map(MediaDTO::new).collect(java.util.stream.Collectors.toList());
  }

  public TwitterEntity toTwitterEntity() {
    return TwitterEntity.builder()
            .media(Optional.ofNullable(media)
                    .orElseGet(Collections::emptyList)
                    .stream().map(MediaDTO::toTwitterMedia).collect(java.util.stream.Collectors.toList()))
            .build();
  }
}
