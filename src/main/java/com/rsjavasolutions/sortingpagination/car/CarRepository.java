package com.rsjavasolutions.sortingpagination.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findAll();

    Optional<CarEntity> findByUuid(String uuid);

    void deleteByUuid(String uuid);
}
