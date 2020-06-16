package az.elvin.hotel.service;

import az.elvin.hotel.model.AdvancedSearchExpensesCategory;
import az.elvin.hotel.model.ExpensesCategory;

import java.util.List;

public interface ExpensesCategoryService {
    List<ExpensesCategory> getExpensesCategoryList() throws Exception;

    boolean addExpensesCategory(ExpensesCategory expensesCategory) throws Exception;

    ExpensesCategory getExpensesCategoryById(Long expensesCategoryId) throws Exception;

    boolean updateExpensesCategory(ExpensesCategory expensesCategory) throws Exception;

    boolean deleteExpensesCategory(Long expensesCategoryId) throws Exception;

    List<ExpensesCategory> searchExpensesCategoryData(String keyword) throws Exception;

    List<ExpensesCategory> advancedSearchExpensesCategoryData(AdvancedSearchExpensesCategory advancedSearchExpensesCategory) throws Exception;
}
