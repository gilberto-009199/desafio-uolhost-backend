package com.gilberto009199.uolhost.desafio_backend.repositories;

import com.gilberto009199.uolhost.desafio_backend.entities.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeroRepository extends JpaRepository<HeroEntity, Long> {


    @Query("""
            SELECT
                e.codename
            FROM
                #{#entityName} e
            GROUP BY e.codename
            """)
    List<String> findCodenamesInUse();
}
