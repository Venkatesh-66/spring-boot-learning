@Query(value = "SELECT * FROM users WHERE name = :name", nativeQuery = true)
List<User> getUsers(@Param("name") String name);