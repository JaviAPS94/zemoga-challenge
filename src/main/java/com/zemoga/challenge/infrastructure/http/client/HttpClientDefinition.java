package com.zemoga.challenge.infrastructure.http.client;

import com.zemoga.challenge.infrastructure.http.dto.TwitterUserTimelineDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface HttpClientDefinition {
  @GET("/1.1/statuses/user_timeline.json")
  Call<List<TwitterUserTimelineDTO>> retrievePartnerStoresByEmail(
          @Query(value = "screen_name") String screenName,
          @Query(value = "count") Integer count
  );
}
