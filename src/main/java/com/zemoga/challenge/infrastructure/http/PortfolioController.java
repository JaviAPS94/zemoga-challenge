package com.zemoga.challenge.infrastructure.http;

import com.zemoga.challenge.domain.actions.portfolio.FindByName;
import com.zemoga.challenge.domain.actions.portfolio.Save;
import com.zemoga.challenge.domain.actions.twitter.FindByUsernameAndSize;
import com.zemoga.challenge.domain.model.entities.Portfolio;
import com.zemoga.challenge.domain.model.entities.TwitterUserTimeline;
import com.zemoga.challenge.infrastructure.http.dto.PortfolioDTO;
import com.zemoga.challenge.infrastructure.http.dto.PortfolioUserWithTwitterTimelineDTO;
import com.zemoga.challenge.infrastructure.http.dto.TwitterUserTimelineDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/portfolio")
@AllArgsConstructor
public class PortfolioController {

  private final FindByName findByName;
  private final FindByUsernameAndSize findByUsernameAndSize;
  private final Save save;

  @GetMapping("/user/{name}")
  public ResponseEntity<PortfolioUserWithTwitterTimelineDTO> portfolioByUserName(
          @Valid @NotBlank @PathVariable String name,
          @Valid @NotBlank @RequestParam(value = "tweets") Integer tweets
  ) {
    Optional<Portfolio> portfolio = findByName.execute(name);

    return portfolio.map(p -> {
              List<TwitterUserTimeline> twitterUserTimeline = findByUsernameAndSize.execute(p.getTwitterUser(), tweets);

              return ResponseEntity.ok(PortfolioUserWithTwitterTimelineDTO.builder()
                      .portfolio(new PortfolioDTO(p))
                      .tweets(twitterUserTimeline.stream().map(TwitterUserTimelineDTO::new)
                              .collect(java.util.stream.Collectors.toList()))
                      .build());
            }
    ).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/user")
  @ResponseStatus(HttpStatus.CREATED)
  public void addUserPortfolio(
          @Valid @RequestBody PortfolioDTO portfolioDTO
  ) {
    save.execute(portfolioDTO.toPortfolio());
  }
}
