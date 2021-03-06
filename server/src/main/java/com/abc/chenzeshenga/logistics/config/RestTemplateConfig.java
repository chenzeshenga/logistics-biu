package com.abc.chenzeshenga.logistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author chenzesheng
 * @since 2020-03-21 22:52
 */
@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
    RestTemplate restTemplate = new RestTemplate(factory);
    //    List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
    //    for (HttpMessageConverter<?> converter : converters) {
    //      if (converter instanceof MappingJackson2XmlHttpMessageConverter) {
    //        try {
    //          List<MediaType> mediaTypeList = new ArrayList<>(converter.getSupportedMediaTypes());
    //          mediaTypeList.add(MediaType.TEXT_XML);
    //          ((MappingJackson2XmlHttpMessageConverter) converter)
    //              .setSupportedMediaTypes(mediaTypeList);
    //        } catch (Exception e) {
    //          e.printStackTrace();
    //        }
    //      }
    //    }
    return restTemplate;
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(10000);
    factory.setReadTimeout(60000);
    return factory;
  }
}
