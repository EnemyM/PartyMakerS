package com.partymaker.mvc.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import java.io.IOException;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)// to enable @Secured,@PreAuthorize
public class Security extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    /* */
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        /*builder.inMemoryAuthentication()
                .withUser("1").password("1").roles("USER").and()
                .withUser("a").password("a").roles("ADMIN");*/
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/signup", "/user/test").permitAll()
                .anyRequest().authenticated()
                .and()
                .requestCache()
                .requestCache(new NullRequestCache())
                .and()
                .httpBasic();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
