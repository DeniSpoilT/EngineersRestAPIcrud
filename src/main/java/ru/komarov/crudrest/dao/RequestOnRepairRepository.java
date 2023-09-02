package ru.komarov.crudrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.komarov.crudrest.model.RequestOnRepair;

@Repository
public interface RequestOnRepairRepository extends JpaRepository<RequestOnRepair, Long> {
}
