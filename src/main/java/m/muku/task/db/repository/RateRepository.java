package m.muku.task.db.repository;

import m.muku.task.db.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate,Long> {
}
