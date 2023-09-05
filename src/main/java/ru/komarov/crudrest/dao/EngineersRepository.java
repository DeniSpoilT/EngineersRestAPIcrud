package ru.komarov.crudrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.komarov.crudrest.model.Engineer;

import java.util.List;
import java.util.Optional;
@Repository
public interface EngineersRepository extends JpaRepository<Engineer, Long> {
    Optional<Engineer> findById(Long id);

    @Query("SELECT DISTINCT e FROM Engineer e LEFT JOIN FETCH e.requests")
    List<Engineer> findAll();

}
