package com.zemoga.challenge.domain.actions.twitter;

import com.zemoga.challenge.domain.model.entities.TwitterUserTimeline;
import com.zemoga.challenge.domain.model.repositories.TwitterRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindByUsernameAndSize {

  private final TwitterRepository twitterRepository;

  public List<TwitterUserTimeline> execute(String username, Integer size) {
    return twitterRepository.findByUsernameAndSize(username, size);
  }
}
