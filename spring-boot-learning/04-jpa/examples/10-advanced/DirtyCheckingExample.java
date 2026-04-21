User user = em.find(User.class, 1L);
user.setName("New Name"); // auto updated