// package apapedia.user.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import apapedia.user.restservice.UserRestService;
// import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// @AllArgsConstructor
// @NoArgsConstructor
// public class SecurityConfiguration {

//     @Autowired
//     private JwtAuthenticationFilter jwtFilter;

//     @Autowired
//     private AuthenticationProvider authenticationProvider;

//     @Autowired
//     private UserDetailsService userDetailsService;
    
//     // @Bean
//     // public UserRestService userDetailsService () {
//     //     return new UserRestService();
//     // }
    
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                 .csrf().disable()
//                 .authorizeRequests().requestMatchers("/authentication/**").permitAll()
//                 .anyRequest().authenticated()
//                 .and()
//                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                 .and()
//                 .authenticationProvider(authenticationProvider)
//                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//         return http.build();
//     }

//     @Bean
//     public AuthenticationProvider authenticationProvider() { 
//         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
//         authenticationProvider.setUserDetailsService(userDetailsService); 
//         authenticationProvider.setPasswordEncoder(passEncoder()); 
//         return authenticationProvider; 
//     }

//     @Bean
//     public BCryptPasswordEncoder passEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Autowired
//     public void configure(AuthenticationManagerBuilder auth) throws Exception{
//         auth
//                 .userDetailsService(userDetailsService)
//                 .passwordEncoder(passEncoder());
//     }
    
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
//         return config.getAuthenticationManager(); 
//     }
    
    
    

    

    

//     // ganti-1
//     // @Bean
//     // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//     //     auth.userDetailsService(userDetailsService).passwordEncoder(passEncoder());
//     // }
    

    

    
// }
