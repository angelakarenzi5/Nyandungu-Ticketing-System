package com.ticketing_system.security;


import com.ticketing_system.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/view/home", "/view/error","/view/feedback","/view/about", "/view/history","/view/signup","/view/login","/feedback/create","/user/create").permitAll() // Allow public access
                .antMatchers("/view/admin/dashboard","/view/user/dashboard","/view/user/tickets","/view/user/profile","/view/admin/feedback","/view/admin/tickets","/view/admin/clients","/ticket/approve/","/ticket/reject/").authenticated()
               // .antMatchers("/maintenance_task/**").authenticated()
                .anyRequest().authenticated() // Require authentication for other endpoints
                .and()
                .formLogin()
                .loginPage("/login") // Specify your custom login page URL
                .defaultSuccessUrl("/login/success") // Add this line
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // Specify the URL to redirect after logout
                .permitAll();
    }
}