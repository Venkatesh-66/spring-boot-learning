@Transactional
public void updateUser(Long id) {
    User user = em.find(User.class, id);
    user.setName("Updated");
}