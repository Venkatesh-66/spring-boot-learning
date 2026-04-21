@Query("SELECT u FROM User u JOIN u.orders o WHERE o.id = :id")
List<User> getUserByOrder(@Param("id") Long id);