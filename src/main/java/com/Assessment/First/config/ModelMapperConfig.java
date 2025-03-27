package com.Assessment.First.config;

import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig
{
    @Bean
    public ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
