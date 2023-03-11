package get.a.big.head.newNRG.users.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security
        .config.annotation.web.builders.HttpSecurity;
import org.springframework.security
        .config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security
        .config.annotation.web.configuration
        .WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security
        .crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtConfiguration jwtConfiguration;

    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/authorization/**").permitAll()
                .antMatchers("/user/**").hasAuthority("READ")
                .antMatchers("/moderator/**").hasAuthority("WRITE")
                .antMatchers("/admin/**").hasAuthority("GOD")
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfiguration);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
