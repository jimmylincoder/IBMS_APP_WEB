package com.suntek.ibms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends WebMvcConfigurerAdapter
{
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        super.configureMessageConverters(converters);
    }

    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}
