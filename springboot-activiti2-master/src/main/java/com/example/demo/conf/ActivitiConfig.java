package com.example.demo.conf;


import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.rest.common.application.DefaultContentTypeResolver;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {



    @Bean
    public RestResponseFactory restResponseFactory()
    {
        return  new RestResponseFactory();
    }
    @Bean
    public DefaultContentTypeResolver contentTypeResolver()
    {
        return  new DefaultContentTypeResolver();
    }

    @Bean
    public ProcessEngineFactoryBean processEngine()
    {
        return  new ProcessEngineFactoryBean();
    }








}


