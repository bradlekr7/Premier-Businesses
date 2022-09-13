package com.revature.business.review.site.security;


import com.revature.business.review.site.security.filter.CustomAuthenticationFilter;
import com.revature.business.review.site.security.filter.CustomAuthorizationFilter;
import com.revature.business.review.site.security.token.JWTGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorizationFilter authorizationFilter;

    private final JWTGenerator jwtGenerator;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        CustomAuthenticationFilter authenticationFilter=
                new CustomAuthenticationFilter( authenticationManagerBean(),jwtGenerator);
        authenticationFilter.setFilterProcessesUrl("/api/auth/login");

        http.authorizeRequests().antMatchers("/api/auth/**").permitAll();
        http.authorizeRequests().antMatchers("/api/app/home").permitAll();
        http.authorizeRequests().antMatchers("/mysql/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();



        http.addFilter(authenticationFilter);
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);


        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Bean
//    @DependsOn("authenticationManagerBean")
//    public CustomAuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager) throws Exception {
//        CustomAuthenticationFilter authenticationFilter=
//                new CustomAuthenticationFilter( authenticationManager,jwtGenerator);
//        authenticationFilter.setFilterProcessesUrl("/api/auth/login");
//        return authenticationFilter;
//    }
}

