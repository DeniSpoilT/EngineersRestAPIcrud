package ru.komarov.crudrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.Optional;

public interface RequestOnRepairRepository extends JpaRepository<RequestOnRepair, Long> {
    Optional<RequestOnRepair> findById(Long id);
}
