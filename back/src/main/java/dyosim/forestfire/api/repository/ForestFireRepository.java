package dyosim.forestfire.api.repository;

import dyosim.forestfire.api.domain.ForestFire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ForestFireRepository extends JpaRepository<ForestFire, Long>{
    List<ForestFire> findByCodeAndDateAfter(int code, LocalDateTime date);
    List<ForestFire> findByDateAfter(LocalDateTime date);
    List<ForestFire> findByDate(LocalDateTime date);
}
