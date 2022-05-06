package ru.itmo.kotiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.model.Cat;

import java.util.List;

@Repository
public interface CatDao extends JpaRepository<Cat, Integer> {
    List<Cat> findCatsByOwnerId(int owner_id);
}
