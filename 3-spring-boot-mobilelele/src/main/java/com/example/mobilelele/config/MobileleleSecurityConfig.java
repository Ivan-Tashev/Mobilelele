package com.example.mobilelele.config;

import com.example.mobilelele.security.MobileleleUserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MobileleleSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MobileleleUserDetailsServiceImpl mobileleleUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public MobileleleSecurityConfig(MobileleleUserDetailsServiceImpl mobileleleUserDetailsService, PasswordEncoder passwordEncoder) {
        this.mobileleleUserDetailsService = mobileleleUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mobileleleUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                .antMatchers("/", "/home", "/users/login", "/users/register",
                        "/offers/all", "/brands/all").permitAll()
                .antMatchers("/offers/add").hasRole("USER") // only for...
                .anyRequest().authenticated() // everything else
                .and() // get the Login form
                .formLogin().loginPage("/users/login")
                .usernameParameter("username") // <input name=“username".. />
                .passwordParameter("password") // <input name=“password" .. />
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login")
                .and() // get Logout
                .logout().logoutUrl("/users/logout") // <form action="@{/users/logout}" method="post">
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}
