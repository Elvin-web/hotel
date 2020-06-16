package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.ExpensesDao;
import az.elvin.hotel.model.Expenses;
import az.elvin.hotel.service.ExpensesService;

import java.util.List;

public class ExpensesServiceImpl implements ExpensesService {
    private ExpensesDao expensesDao;

    public ExpensesServiceImpl(ExpensesDao expensesDao) {
        this.expensesDao = expensesDao;
    }

    @Override
    public List<Expenses> getExpensesList() throws Exception {
        return expensesDao.getExpensesList();
    }

    @Override
    public List<Expenses> getExpensesListByExpensesCategoryId(long expensesCategoryId) throws Exception {
        return expensesDao.getExpensesListByExpensesCategoryId(expensesCategoryId);
    }

    @Override
    public boolean addExpenses(Expenses expenses) throws Exception {
        return expensesDao.addExpenses(expenses);
    }

    @Override
    public Expenses getExpensesById(Long expensesId) throws Exception {
        return expensesDao.getExpensesById(expensesId);
    }

    @Override
    public boolean updateExpenses(Expenses expenses) throws Exception {
        return expensesDao.updateExpenses(expenses);
    }

    @Override
    public boolean deleteExpenses(Long expensesId) throws Exception {
        return expensesDao.deleteExpenses(expensesId);
    }

    @Override
    public List<Expenses> searchExpensesData(String keyword) throws Exception {
        return expensesDao.searchExpensesData(keyword);
    }
}
