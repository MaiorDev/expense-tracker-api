package maiordev.expense_tracker_api.service;

import jakarta.transaction.Transactional;
import maiordev.expense_tracker_api.entity.CategoryEntity;
import maiordev.expense_tracker_api.entity.ExpenseEntity;
import maiordev.expense_tracker_api.entity.UserEntity;
import maiordev.expense_tracker_api.model.ExpenseDTO;
import maiordev.expense_tracker_api.model.ExpenseResponseDTO;
import maiordev.expense_tracker_api.repository.CategoryRepository;
import maiordev.expense_tracker_api.repository.ExpenseRepository;
import maiordev.expense_tracker_api.repository.UserRepository;
import maiordev.expense_tracker_api.util.GenericResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    // ✅ Mapper privado para convertir entity a DTO
    private ExpenseResponseDTO toDTO(ExpenseEntity e) {
        return new ExpenseResponseDTO(
                e.getId(),
                e.getDescription(),
                e.getAmount(),
                e.getDate(),
                e.getCategory().getName(),
                e.getUser().getUsername()
        );
    }

    @Override
    public GenericResponse getExpenses(String username) {
        try {
            List<ExpenseResponseDTO> expenses = expenseRepository
                    .findByUserUsername(username)
                    .stream()
                    .map(this::toDTO) // ✅
                    .toList();

            return new GenericResponse(0, "Expenses retrieved", expenses);
        } catch (Exception e) {
            return new GenericResponse(2, "Server error: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse getExpenseById(Long id) {
        try {
            Optional<ExpenseEntity> expense = expenseRepository.findById(id);
            if (expense.isEmpty()) {
                return new GenericResponse(1, "Expense not found", null);
            }
            return new GenericResponse(0, "Expense retrieved", toDTO(expense.get())); // ✅
        } catch (Exception e) {
            return new GenericResponse(2, "Server error: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse addExpense(ExpenseDTO expenseDTO) {
        try {
            Optional<UserEntity> user = userRepository.findByUsername(expenseDTO.getUsername());
            if (user.isEmpty()) return new GenericResponse(1, "User not found", null);

            Optional<CategoryEntity> category = categoryRepository.findById(expenseDTO.getCategoryId());
            if (category.isEmpty()) return new GenericResponse(1, "Category not found", null);

            ExpenseEntity expense = new ExpenseEntity();
            expense.setDescription(expenseDTO.getDescription());
            expense.setAmount(expenseDTO.getAmount());
            expense.setDate(LocalDate.parse(expenseDTO.getDate()));
            expense.setUser(user.get());
            expense.setCategory(category.get());

            return new GenericResponse(0, "Expense added", toDTO(expenseRepository.save(expense))); // ✅
        } catch (Exception e) {
            return new GenericResponse(2, "Server error: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse updateExpense(Long id, ExpenseDTO expenseDTO) {
        try {
            Optional<ExpenseEntity> existing = expenseRepository.findById(id);
            if (existing.isEmpty()) return new GenericResponse(1, "Expense not found", null);

            Optional<CategoryEntity> category = categoryRepository.findById(expenseDTO.getCategoryId());
            if (category.isEmpty()) return new GenericResponse(1, "Category not found", null);

            ExpenseEntity expense = existing.get();
            expense.setDescription(expenseDTO.getDescription());
            expense.setAmount(expenseDTO.getAmount());
            expense.setDate(LocalDate.parse(expenseDTO.getDate()));
            expense.setCategory(category.get());

            return new GenericResponse(0, "Expense updated", toDTO(expenseRepository.save(expense))); // ✅
        } catch (Exception e) {
            return new GenericResponse(2, "Server error: " + e.getMessage(), null);
        }
    }

    @Override
    public GenericResponse getExpensesByPeriod(String username, LocalDate startDate, LocalDate endDate) {
        try {
            List<ExpenseResponseDTO> expenses = expenseRepository
                    .findByUserUsernameAndDateBetween(username, startDate, endDate)
                    .stream()
                    .map(this::toDTO) // ✅
                    .toList();

            return new GenericResponse(0, "Expenses retrieved", expenses);
        } catch (Exception e) {
            return new GenericResponse(2, "Server error: " + e.getMessage(), null);
        }
    }
    // ─── DELETE ────────────────────────────────────────────────────────────────
    @Override
    public GenericResponse deleteExpense(Long id) {
        try {
            Optional<ExpenseEntity> expense = expenseRepository.findById(id);
            if (expense.isEmpty()) {
                return new GenericResponse(1, "Expense not found", null);
            }

            expenseRepository.deleteById(id);
            return new GenericResponse(0, "Expense deleted", null);

        } catch (Exception e) {
            return new GenericResponse(2, "Server error: " + e.getMessage(), null);
        }
    }

}