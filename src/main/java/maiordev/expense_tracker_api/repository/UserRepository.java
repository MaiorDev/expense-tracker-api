package maiordev.expense_tracker_api.repository;

import maiordev.expense_tracker_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
