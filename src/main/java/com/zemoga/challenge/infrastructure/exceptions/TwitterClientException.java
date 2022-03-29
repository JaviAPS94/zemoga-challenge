package com.zemoga.challenge.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TwitterClientException extends RuntimeException {
  private HttpStatus type;
  private String message;
  private Throwable cause;
  private String countryCode;
  private String code;

  public static class TwitterClientExceptionBuilder {
    public void throwIt() throws TwitterClientException {
      throw this.build();
    }

    public TwitterClientExceptionBuilder badRequest() {
      this.type = HttpStatus.BAD_REQUEST;
      return this;
    }

    public TwitterClientExceptionBuilder internalServerError() {
      this.type = HttpStatus.INTERNAL_SERVER_ERROR;
      return this;
    }

    public TwitterClientExceptionBuilder message(String format, Object... args) {
      this.message = String.format(format, args);
      return this;
    }
  }
}
