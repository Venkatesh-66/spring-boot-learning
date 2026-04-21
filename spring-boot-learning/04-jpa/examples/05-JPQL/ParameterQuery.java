@Query("SELECT u FROM User u WHERE u.name = :name")
List<User> findByName(@Param("name") String name);