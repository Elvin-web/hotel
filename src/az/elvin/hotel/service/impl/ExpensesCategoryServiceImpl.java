package az.elvin.hotel.service.impl;

import az.elvin.hotel.dao.ExpensesCategoryDao;
import az.elvin.hotel.model.AdvancedSearchExpensesCategory;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.service.ExpensesCategoryService;

import java.util.List;

public class ExpensesCategoryServiceImpl implements ExpensesCategoryService {
    private ExpensesCategoryDao expensesCategoryDao;

    public ExpensesCategoryServiceImpl(ExpensesCategoryDao expensesCategoryDao) {
        this.expensesCategoryDao = expensesCategoryDao;
    }

    @Override
    public List<ExpensesCategory> getExpensesCategoryList() throws Exception {
        return expensesCategoryDao.getExpensesCategoryList();
    }

    @Override
    public boolean addExpensesCategory(ExpensesCategory expensesCategory) throws Exception {
        return expensesCategoryDao.addExpensesCategory(expensesCategory);
    }

    @Override
    public ExpensesCategory getExpensesCategoryById(Long expensesCategoryId) throws Exception {
        return expensesCategoryDao.getExpensesCategoryById(expensesCategoryId);
    }

    @Override
    public boolean updateExpensesCategory(ExpensesCategory expensesCategory) throws Exception {
        return expensesCategoryDao.updateExpensesCategory(expensesCategory);
    }

    @Override
    public boolean deleteExpensesCategory(Long expensesCategoryId) throws Exception {
        return expensesCategoryDao.deleteExpensesCategory(expensesCategoryId);
    }

    @Override
    public List<ExpensesCategory> searchExpensesCategoryData(String keyword) throws Exception {
        return expensesCategoryDao.searchExpensesCategoryData(keyword);
    }

    @Override
    public List<ExpensesCategory> advancedSearchExpensesCategoryData(AdvancedSearchExpensesCategory advancedSearchExpensesCategory) throws Exception {
        return expensesCategoryDao.advancedSearchExpensesCategoryData(advancedSearchExpensesCategory);
    }
}
