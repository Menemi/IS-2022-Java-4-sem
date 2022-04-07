package ru.itmo.kotiki.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;
import ru.itmo.kotiki.model.*;

import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    @Query("select Owner from Cat where Cat.id = :id")
    Optional<Owner> findOwnerById(@Param("id") Long id);
}
