package ru.komarov.crudrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.Optional;
@Repository
public interface RequestOnRepairRepository extends JpaRepository<RequestOnRepair, Long> {
}
