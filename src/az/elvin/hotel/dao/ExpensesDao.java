package az.elvin.hotel.dao;

import az.elvin.hotel.model.Expenses;

import java.util.List;

public interface ExpensesDao {
    List<Expenses> getExpensesList() throws Exception;

    List<Expenses> getExpensesListByExpensesCategoryId(long expensesCategoryId) throws Exception;

    boolean addExpenses(Expenses expenses) throws Exception;

    Expenses getExpensesById(Long expensesId) throws Exception;

    boolean updateExpenses(Expenses expenses) throws Exception;

    boolean deleteExpenses(Long expensesId) throws Exception;

    List<Expenses> searchExpensesData(String keyword) throws Exception;
}
