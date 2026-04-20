package maiordev.expense_tracker_api.service;


import maiordev.expense_tracker_api.model.ExpenseDTO;
import maiordev.expense_tracker_api.util.GenericResponse;

import java.time.LocalDate;

public interface ExpenseService {

    GenericResponse getExpenses(String username);
    GenericResponse addExpense(ExpenseDTO expenseDTO);
    GenericResponse getExpenseById(Long id);
    GenericResponse deleteExpense(Long id);
    GenericResponse updateExpense(Long id, ExpenseDTO expenseDTO);
    GenericResponse getExpensesByPeriod(String username, LocalDate startDate, LocalDate endDate);
}
