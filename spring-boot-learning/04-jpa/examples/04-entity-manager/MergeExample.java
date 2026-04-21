User user = userRepository.findById(1L).orElse(null);

if (user != null) {
    user.setName("Updated Name");
    user.setEmail("updated@example.com");

    // UPDATE in DB
    userRepository.save(user);
}