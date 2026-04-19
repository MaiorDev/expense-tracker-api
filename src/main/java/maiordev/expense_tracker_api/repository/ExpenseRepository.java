package maiordev.expense_tracker_api.repository;

import maiordev.expense_tracker_api.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository  extends JpaRepository<ExpenseEntity, Long> {
    @Query("SELECT e FROM ExpenseEntity e JOIN FETCH e.category " +
            "WHERE e.user.username = :username " +
            "AND e.date >= :startDate")
    List<ExpenseEntity> findUserExpensesFromDate(@Param("username") String username,
                                                 @Param("startDate") LocalDate startDate);
}
