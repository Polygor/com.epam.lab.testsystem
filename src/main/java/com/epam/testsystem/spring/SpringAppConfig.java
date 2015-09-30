package com.epam.testsystem.spring;

import com.epam.testsystem.action.LoginAction;
import com.epam.testsystem.security.SecurityConfig;
import com.epam.testsystem.service.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jndi.JndiTemplate;

import javax.mail.Session;
import javax.naming.NamingException;

@Configuration
@ComponentScan(basePackageClasses = {
        LoginAction.class,
        TestService.class
})
@Import({SecurityConfig.class, PersistenceConfig.class})
public class SpringAppConfig {
    @Bean
    public Session session() throws NamingException {
        JndiTemplate jndi = new JndiTemplate();
        Session session = (Session) jndi.lookup("java:jboss/mail/gmail"); //String to work with JBOSS Wildfly mail server
        return session;
    }
}
