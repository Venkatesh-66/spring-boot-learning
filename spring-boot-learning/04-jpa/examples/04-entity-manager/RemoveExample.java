User user = userRepository.findById(1L).orElse(null);

if (user != null) {
    userRepository.delete(user);
}