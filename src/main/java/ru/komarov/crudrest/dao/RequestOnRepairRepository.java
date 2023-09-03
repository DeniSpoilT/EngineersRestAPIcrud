package ru.komarov.crudrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.List;

@Repository
public interface RequestOnRepairRepository extends JpaRepository<RequestOnRepair, Long> {
    List<RequestOnRepair> findAllByEngineerId(Long id);

    @Query("FROM RequestOnRepair r JOIN FETCH r.engineer")
    List<RequestOnRepair> findAll();
}
