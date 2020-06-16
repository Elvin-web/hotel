package az.elvin.hotel.dao;

import az.elvin.hotel.model.AdvancedSearchExpensesCategory;
import az.elvin.hotel.model.ExpensesCategory;

import java.util.List;

public interface ExpensesCategoryDao {
    List<ExpensesCategory> getExpensesCategoryList() throws Exception;

    boolean addExpensesCategory(ExpensesCategory expensesCategory) throws Exception;

    ExpensesCategory getExpensesCategoryById(Long expensesCategoryId) throws Exception;

    boolean updateExpensesCategory(ExpensesCategory expensesCategory) throws Exception;

    boolean deleteExpensesCategory(Long expensesCategoryId) throws Exception;

    List<ExpensesCategory> searchExpensesCategoryData(String keyword) throws Exception;

    List<ExpensesCategory> advancedSearchExpensesCategoryData(AdvancedSearchExpensesCategory advancedSearchExpensesCategory) throws Exception;

}
