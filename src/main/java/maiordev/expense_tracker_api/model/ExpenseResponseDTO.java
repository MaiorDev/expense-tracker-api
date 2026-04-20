package maiordev.expense_tracker_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDTO {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private String categoryName; // ✅ solo el nombre, no el objeto completo
    private String username;     // ✅ solo el username, no el objeto completo
}