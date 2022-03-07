package com.revature.quizzard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// when using annotations for configuration, you need a config class
@Configuration
// we need to tell Spring where to look for Spring beans (components)
@ComponentScan("com.revature")
public class AppConfig {

}
