package ru.ansysan.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class FilterConfig {

    @Value("${filter.enabled}")
    private boolean filterEnabled;

}

