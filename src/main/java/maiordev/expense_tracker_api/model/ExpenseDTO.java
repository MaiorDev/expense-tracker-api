package maiordev.expense_tracker_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    private String description;
    private Double amount;
    private String date;        // "2024-01-01"
    private Long categoryId;    // ✅ era Integer, cambiado a Long para coincidir con el entity
    private String username;    // ✅ era Integer, ahora el username del user autenticado
}