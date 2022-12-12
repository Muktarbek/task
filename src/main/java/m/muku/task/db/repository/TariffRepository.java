package m.muku.task.db.repository;

import m.muku.task.db.entity.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff,Long> {
}
