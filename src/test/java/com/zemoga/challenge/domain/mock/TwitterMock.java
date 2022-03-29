package com.zemoga.challenge.domain.mock;

import com.zemoga.challenge.domain.model.entities.TwitterEntity;
import com.zemoga.challenge.domain.model.entities.TwitterMedia;
import com.zemoga.challenge.domain.model.entities.TwitterUserTimeline;

import java.util.ArrayList;
import java.util.List;

public class TwitterMock {
  public static List<TwitterUserTimeline> createList() {
    List<TwitterUserTimeline> twitterUserTimelineList = new ArrayList<>();

    List<TwitterMedia> twitterMedia = new ArrayList<>();

    twitterMedia.add(TwitterMedia.builder()
            .mediaUrlHttps("https://pbs.twimg.com/media/Dy_7-zUU0AAZQ-q.jpg")
            .build());

    twitterUserTimelineList.add(TwitterUserTimeline.builder()
            .id(1L)
            .createdAt("2022-02-02T02:02:02Z")
            .text("Lorem")
            .entities(TwitterEntity.builder()
                    .media(twitterMedia)
                    .build())
            .build());

    return twitterUserTimelineList;
  }
}
