

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for simplicity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public").permitAll() // no login needed
                .anyRequest().authenticated() // others need login
            )
            .httpBasic(); // enable basic auth

        return http.build();
    }

   @Bean
   public UserDetailsService userDetailsService() {
    UserDetails user = User
        .withUsername("user")
        .password(passwordEncoder().encode("1234"))
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

