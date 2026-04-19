package maiordev.expense_tracker_api.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private Integer code;
    private String message;
    private Object response;
}
