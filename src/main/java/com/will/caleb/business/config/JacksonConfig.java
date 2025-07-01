package com.will.caleb.business.config;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> builder.modulesToInstall(new JavaTimeModule());
    }
}
