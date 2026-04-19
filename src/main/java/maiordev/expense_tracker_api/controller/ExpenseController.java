package maiordev.expense_tracker_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    @RequestMapping("/add")
    public String addExpense(){
        return "Expense added";
    }
    @RequestMapping("/list")
    public String listExpense(){
        return "Expense list";
    }
    @RequestMapping("/delete")
    public String deleteExpense(){
        return "Expense deleted";
    }
    @RequestMapping("/update")
    public String updateExpense(){
        return "Expense updated";
    }
    @RequestMapping("/get")
    public String getExpense(){
        return "Expense get";
    }

}
