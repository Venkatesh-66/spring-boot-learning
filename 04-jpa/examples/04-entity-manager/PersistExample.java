User user = new User();

user.setName("John");
user.setEmail("john@example.com");

// Save to DB (INSERT)
userRepository.save(user);