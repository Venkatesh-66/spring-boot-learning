List<User> users = repo.findAll();
users.forEach(u -> u.getOrders()); // multiple queries