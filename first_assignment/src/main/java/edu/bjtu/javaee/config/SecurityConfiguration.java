package edu.bjtu.javaee.config;

import edu.bjtu.javaee.filter.LoginSuccessHandler;
import edu.bjtu.javaee.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.bjtu.javaee.service.UserService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/registration**",
                            "/js/**",
                            "/style/css/**",
                            "/img/**",
                            "/webjars/**").permitAll()
                    //.anyRequest().authenticated()
                .antMatchers("/teacher").hasAnyRole("Teacher","USER")
                .antMatchers("/student").hasAnyRole("Student","USER")
                .antMatchers("/user").hasRole("USER")
                .and()
                    .formLogin()
                        .loginPage("/login").successHandler(new LoginSuccessHandler())
                            .permitAll()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                .permitAll();
       // SecurityFilter sf =new SecurityFilter(); //过滤器添加
      //  http.addFilterAt(sf,sf.getClass());
        http.sessionManagement().invalidSessionUrl("/invalidsession").maximumSessions(1);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override//认证
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
