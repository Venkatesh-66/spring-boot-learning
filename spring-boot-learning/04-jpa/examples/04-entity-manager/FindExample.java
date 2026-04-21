User user = userRepository.findById(1L).orElse(null);

if (user != null) {
    System.out.println(user.getName());
}