package com.frombelow.web.app.repository;

import com.frombelow.web.app.entity.Liga;
import com.frombelow.web.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> findUsersByLigaId(int liga);

    @Query(value = "Select l from User u " +
            "INNER JOIN u.liga l " +
            "WHERE u.username LIKE :username")
    List<Liga> findLigasByUsername(String username);
}
