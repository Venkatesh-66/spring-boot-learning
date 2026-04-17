

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