package com.zemoga.challenge.domain.actions.twitter;

import com.zemoga.challenge.domain.mock.TwitterMock;
import com.zemoga.challenge.domain.model.entities.TwitterUserTimeline;
import com.zemoga.challenge.domain.model.repositories.TwitterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FindByUsernameAndSizeTest {
  @Mock
  private TwitterRepository twitterRepository;

  @InjectMocks
  private FindByUsernameAndSize findByUsernameAndSize;

  @Test
  public void findTwitterTimelineByUsernameAndSize_ok() {
    List<TwitterUserTimeline> twitterUserTimelineMock = TwitterMock.createList();
    String userName = "userName";
    Integer size = 5;

    doReturn(twitterUserTimelineMock)
            .when(twitterRepository)
            .findByUsernameAndSize(userName, size);

    List<TwitterUserTimeline> result = findByUsernameAndSize.execute(userName, size);

    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getText()).isEqualTo("Lorem");
    assertThat(result.get(0).getId()).isEqualTo(1L);
    assertThat(result.get(0).getCreatedAt()).isEqualTo("2022-02-02T02:02:02Z");
    assertThat(result.get(0).getEntities().getMedia().size()).isEqualTo(1);
    assertThat(result.get(0).getEntities().getMedia().get(0).getMediaUrlHttps()).isEqualTo("https://pbs.twimg.com/media/Dy_7-zUU0AAZQ-q.jpg");

    verify(twitterRepository, times(1))
            .findByUsernameAndSize(userName, size);
  }
}
