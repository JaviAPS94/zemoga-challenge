package com.zemoga.challenge.infrastructure.http.client.filter;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public final class TwitterTokenInterceptor implements Interceptor {

  public static final String AUTHORIZATION_HEADER = "Authorization";
  private final String token;

  public TwitterTokenInterceptor(String token) {
    this.token = token;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request.Builder builder = chain.request().newBuilder();
    builder.addHeader(AUTHORIZATION_HEADER, token);

    return chain.proceed(builder.build());
  }
}
