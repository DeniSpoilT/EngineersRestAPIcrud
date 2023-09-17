package ru.komarov.crudrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.model.Engineer;

import java.util.List;
import java.util.Optional;
@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long> {
    Optional<Engineer> findById(Long id);

    List<Engineer> findAll();
    @Query("select distinct e from Engineer e join fetch  e.requests r")
    List<Engineer> findAllEngineersWithRequests();

}
