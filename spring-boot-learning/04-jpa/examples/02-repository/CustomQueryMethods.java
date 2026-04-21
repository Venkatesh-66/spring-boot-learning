@Query("SELECT u FROM User u WHERE u.name = :name")
List<User> getByName(@Param("name") String name);