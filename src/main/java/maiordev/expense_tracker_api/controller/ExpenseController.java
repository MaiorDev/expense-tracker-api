package maiordev.expense_tracker_api.controller;

import maiordev.expense_tracker_api.model.ExpenseDTO;
import maiordev.expense_tracker_api.service.ExpenseService;
import maiordev.expense_tracker_api.util.GenericResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @GetMapping("/list")
    public ResponseEntity<GenericResponse> listExpense(Authentication authentication) {
        String username = authentication.getName();
        GenericResponse response = expenseService.getExpenses(username);
        return response.getCode().equals(0) ? ResponseEntity.ok(response) :ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @GetMapping("/period")
    public ResponseEntity<GenericResponse> getExpensesByPeriod(
            Authentication authentication,
            @RequestBody Map<String, LocalDate> period) {

        String username = authentication.getName();
        GenericResponse response = expenseService.getExpensesByPeriod(
                username,
                period.get("startDate"),
                period.get("endDate")
        );
        return response.getCode().equals(0)
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getExpense(@PathVariable Long id) {
        GenericResponse response = expenseService.getExpenseById(id);
        return response.getCode().equals(0)
                ? ResponseEntity.ok(response)
                :ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addExpense(@RequestBody ExpenseDTO expenseDTO) {
        GenericResponse response = expenseService.addExpense(expenseDTO);
        return response.getCode().equals(0)
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenericResponse> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        GenericResponse response = expenseService.updateExpense(id, expenseDTO);
        return response.getCode().equals(0)
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteExpense(@PathVariable Long id) {
        GenericResponse response = expenseService.deleteExpense(id);
        return response.getCode().equals(0)
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}