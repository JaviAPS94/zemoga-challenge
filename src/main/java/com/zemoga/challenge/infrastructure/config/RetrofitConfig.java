package com.zemoga.challenge.infrastructure.config;

import com.zemoga.challenge.infrastructure.http.client.HttpClientDefinition;
import com.zemoga.challenge.infrastructure.http.client.filter.TwitterTokenInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {

  @Bean
  @Primary
  public OkHttpClient getHttpClientBuilder(@Value("${TWITTER_TOKEN}") String token) {
    return new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(7, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(new TwitterTokenInterceptor(token))
            .build();
  }

  @Bean("retrofitTwitter")
  @Primary
  public Retrofit retrofit(
          OkHttpClient okHttpClient, @Value("${TWITTER_SERVICES_URL}") String baseUrl) {

    return new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(okHttpClient)
            .build();
  }

  @Bean("httpClientDefinition")
  public HttpClientDefinition retrofitApi(@Qualifier("retrofitTwitter") Retrofit retrofit) {
    return retrofit.create(HttpClientDefinition.class);
  }
}
