package com.zemoga.challenge.infrastructure.http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zemoga.challenge.domain.model.entities.TwitterMedia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaDTO {
  @JsonProperty("media_url_https")
  private String mediaUrlHttps;

  public MediaDTO(TwitterMedia twitterMedia) {
    this.mediaUrlHttps = twitterMedia.getMediaUrlHttps();
  }

  public TwitterMedia toTwitterMedia() {
    return TwitterMedia.builder()
            .mediaUrlHttps(mediaUrlHttps)
            .build();
  }
}
