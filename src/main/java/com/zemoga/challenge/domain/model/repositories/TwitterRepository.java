package com.zemoga.challenge.domain.model.repositories;

import com.zemoga.challenge.domain.model.entities.TwitterUserTimeline;

import java.util.List;

public interface TwitterRepository {
    List<TwitterUserTimeline> findByUsernameAndSize(String username, Integer size);
}
