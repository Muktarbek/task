package m.muku.task.db.repository;

import m.muku.task.db.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent,Long> {
}
