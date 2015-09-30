package com.epam.testsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Pattern;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//Security config class, controls the access to the certain pages
    public static final String STATIC = "/static/**";
    public static final String WEBJARS = "/webjars/**";
    public static final String LOGIN = "/login";
    public static final String LOGIN_DO = "/login.do";
    public static final String ADMINSLASH = "/admin";
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(STATIC, WEBJARS);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       // Analog of security filter that controls the access to the certain pages
        http
                .anonymous().and()
                .authorizeRequests()
                .antMatchers("/login", "/login.do").permitAll()
                .antMatchers("/admin", "/admin/**").hasRole("ADMIN")
                .antMatchers("/", "/index.do").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.do")
                .loginProcessingUrl("/login")
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
                    @Override
                    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
                        String targetUrl = super.determineTargetUrl(request, response);
                        boolean role_admin = getCurrentlyAuthenticatedUser().getAuthorities()
                                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
                        boolean role_user = getCurrentlyAuthenticatedUser().getAuthorities()
                                .contains(new SimpleGrantedAuthority("ROLE_USER"));
                        if (targetUrl.equals("/") && role_admin) {
                            targetUrl = "/admin/testList.do";
                        } else if (targetUrl.equals("/") && role_user)
                            targetUrl = "/index.do";
                        return targetUrl;
                    }
                })
                .failureUrl("/login.do?error=1")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/WEB-INF/pages/common/403.jsp");

        http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
            private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
            private RegexRequestMatcher apiMatcher = new RegexRequestMatcher(".*\\.rest", null);

            @Override
            public boolean matches(HttpServletRequest request) {
                // No CSRF due to allowedMethod
                if (allowedMethods.matcher(request.getMethod()).matches())
                    return false;

                // No CSRF due to api call
                if (apiMatcher.matches(request))
                    return false;

                // CSRF for everything else that is not an API call or an allowedMethod
                return true;
            }
        });
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new SecurityUserDetailsService();
    }
}