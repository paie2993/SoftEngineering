package com.yakuza.backend.Config;

import com.yakuza.backend.JWTUtils.JWTAuthenticationEntryPoint;
import com.yakuza.backend.JWTUtils.JWTFilter;
import com.yakuza.backend.JWTUtils.JWTUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private JWTUserDetailsService userDetailsService;
    @Autowired
    private JWTFilter filter;

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // anyone may attempt to log in
                .antMatchers("/user/login").permitAll()
                // anyone may register
                .antMatchers("/user/register").permitAll()
                // anyone may view the api documentation
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                // only chairs may modify the details of a conference
                .antMatchers(HttpMethod.PUT ,"/conference/{id}").hasAuthority("CHAIR")
                // only chairs may get the list of papers submitted to a conference
                .antMatchers(HttpMethod.GET, "/conference/{id}/papers").hasAuthority("CHAIR")
                // only reviewers may get the list of all papers
                .antMatchers(HttpMethod.GET, "/paper/").hasAuthority("REVIEWER")
                // only authors may add papers
                .antMatchers(HttpMethod.POST, "/paper/").hasAuthority("AUTHOR")
                // only chairs may decide on papers
                .antMatchers(HttpMethod.PUT, "/conference/{id}/papers/{paperId}/decideOnPaper").hasAuthority("CHAIR")
                // only chairs may assign a paper to a session
                .antMatchers(HttpMethod.PUT, "/conference/{id}/sessions/{session_id}/papers").hasAuthority("CHAIR")
                // only authors may submit a paper to a conference
                .antMatchers(HttpMethod.PUT, "/conference/{id}/submissions").hasAuthority("AUTHOR")
                // only reviewers may bid on a paper
                .antMatchers(HttpMethod.PUT, "/paper/{id}/bids").hasAuthority("REVIEWER")
                // only reviewers may do reviewer stuff
                .antMatchers("/reviewer/**").hasAuthority("REVIEWER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
