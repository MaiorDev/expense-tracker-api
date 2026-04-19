package maiordev.expense_tracker_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table( name = "expense")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
}
