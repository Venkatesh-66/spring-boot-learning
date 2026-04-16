Page<User> findAll(Pageable pageable);

Pageable pageable = PageRequest.of(0, 10);
repo.findAll(pageable);