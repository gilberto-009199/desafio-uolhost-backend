package com.gilberto009199.uolhost.desafio_backend.repositories;

import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HeroRepository extends JpaRepository<HeroEntity, Long> {


    @Query("""
            SELECT
                e.codename
            FROM
                #{#entityName} e
            GROUP BY e.codename
            """)
    List<String> findCodenamesInUse();

    @Query("""
            SELECT
                e
            FROM
                #{#entityName} e
            WHERE e.email = :email
            """)
    Optional<HeroEntity> findByEmail(@Param("email") String email); 
}
