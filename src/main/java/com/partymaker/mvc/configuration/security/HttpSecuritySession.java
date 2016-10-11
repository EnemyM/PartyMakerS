package com.partymaker.mvc.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import java.io.IOException;

/**
 * Created by anton on 10/10/16.
 */
@Configuration
@EnableRedisHttpSession // enable redis import
public class HttpSecuritySession {

    /* integration to use HTTP headers */
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    /* connects Spring Session to the Redis Server */
    @Bean
    public JedisConnectionFactory connectionFactory() throws IOException {
        return new JedisConnectionFactory();
    }
}
