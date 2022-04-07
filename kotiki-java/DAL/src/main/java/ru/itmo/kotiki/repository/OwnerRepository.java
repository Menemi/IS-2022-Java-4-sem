package ru.itmo.kotiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;
import ru.itmo.kotiki.model.*;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("select Cat from Owner where Owner.id = :id")
    Optional<Cat> findCatById(@Param("id") Long id);
}
