package ru.itmo.kotiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}