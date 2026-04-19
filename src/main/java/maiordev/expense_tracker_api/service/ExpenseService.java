package maiordev.expense_tracker_api.service;

import maiordev.expense_tracker_api.util.GenericResponse;

public interface ExpenseService {
    GenericResponse addExpense();
    GenericResponse getExpenses();
    GenericResponse getExpensesByPeriod();
    GenericResponse getExpenseById();
    GenericResponse deleteExpense();
    GenericResponse updateExpense();
}
