package com.example.springsecuritydemo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /* 1. IN-MEMORY AUTHENTICATION >>>>>>>>>>
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("pass")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("pass")
                .roles("ADMIN");
    }*/

    // 2. JDBC (Default Schema) AUTHENTICATION >>>>>>>>>>
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .withDefaultSchema()
            .withUser(
                    User.withUsername("user")
                        .password("pass")
                        .roles("USER")
            )
            .withUser(
                    User.withUsername("admin")
                        .password("pass")
                        .roles("ADMIN")
            );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/employees").hasRole("ADMIN")
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
