package com.epam.testsystem.spring;

import com.epam.testsystem.rest.UserTestController;
import com.epam.testsystem.service.TestService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {
        UserTestController.class
        ,TestService.class
})
public class RestConfig {
        //REST CONFIG, google Spring Rest
}