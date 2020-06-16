package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.ExpensesCategoryDao;
import az.elvin.hotel.model.AdvancedSearchExpensesCategory;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExpensesCategoryDaoImpl implements ExpensesCategoryDao {
    @Override
    public List<ExpensesCategory> getExpensesCategoryList() throws Exception {
        List<ExpensesCategory> expensesCategoryList = new ArrayList<ExpensesCategory>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT e.id_expenses_type,\n"
                + "         e.expenses_type\n"
                + "    FROM expenses_type e\n"
                + "   WHERE e.active = 1\n"
                + "ORDER BY e.id_expenses_type";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ExpensesCategory expensesCategory = new ExpensesCategory();
                    expensesCategory.setId(rs.getLong("id_expenses_type"));
                    expensesCategory.setExpensesType(rs.getString("expenses_type"));
                    expensesCategoryList.add(expensesCategory);
                }

            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JdbcUtility.close(c, ps, rs);
        }
        return expensesCategoryList;
    }

    @Override
    public boolean addExpensesCategory(ExpensesCategory expensesCategory) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  expenses_type (expenses_type) " + " VALUES (?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, expensesCategory.getExpensesType());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public ExpensesCategory getExpensesCategoryById(Long expensesCategoryId) throws Exception {
        ExpensesCategory expensesCategory = new ExpensesCategory();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql ="SELECT e.id_expenses_type,\n"
                + "         e.expenses_type\n"
                + "    FROM expenses_type e\n"
                + "   WHERE e.active = 1\n"
                + "AND e.id_expenses_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, expensesCategoryId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    expensesCategory.setId(rs.getLong("id_expenses_type"));
                    expensesCategory.setExpensesType(rs.getString("expenses_type"));

                } else {
                    expensesCategory = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return expensesCategory;
    }

    @Override
    public boolean updateExpensesCategory(ExpensesCategory expensesCategory) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  expenses_type SET expenses_type=? WHERE id_expenses_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, expensesCategory.getExpensesType());
                ps.setLong(2, expensesCategory.getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deleteExpensesCategory(Long expensesCategoryId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  expenses_type SET active = 0 " + " WHERE id_expenses_type = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, expensesCategoryId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public List<ExpensesCategory> searchExpensesCategoryData(String keyword) throws Exception {
        List<ExpensesCategory> expensesCategoryList = new ArrayList<ExpensesCategory>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT e.id_expenses_type,\n"
                + "         e.expenses_type\n"
                + "    FROM expenses_type e\n"
                + "   WHERE e.active = 1\n"
                + "AND ( LOWER(expenses_type) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ExpensesCategory expensesCategory = new ExpensesCategory();
                    expensesCategory.setId(rs.getLong("id_expenses_type"));
                    expensesCategory.setExpensesType(rs.getString("expenses_type"));
                    expensesCategoryList.add(expensesCategory);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return expensesCategoryList;
    }

    @Override
    public List<ExpensesCategory> advancedSearchExpensesCategoryData(AdvancedSearchExpensesCategory advancedSearchExpensesCategory) throws Exception {
        List<ExpensesCategory> expensesCategoryList = new ArrayList<ExpensesCategory>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT e.id_expenses_type,\n"
                + "         e.expenses_type\n"
                + "    FROM expenses_type e\n"
                + "   WHERE e.active = 1\n";
        try {
            c = DbHelper.getConnection();
            if (c != null) {

                if (advancedSearchExpensesCategory.getExpensesType() != null && advancedSearchExpensesCategory.getExpensesType().isEmpty()) {
                    sql += " AND e.expenses_type = " + advancedSearchExpensesCategory.getExpensesType();
                }
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ExpensesCategory expensesCategory = new ExpensesCategory();
                    expensesCategory.setId(rs.getLong("id_expenses_type"));
                    expensesCategory.setExpensesType(rs.getString("expenses_type"));
                    expensesCategoryList.add(expensesCategory);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return expensesCategoryList;
    }
}
