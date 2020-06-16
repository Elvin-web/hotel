package az.elvin.hotel.dao.impl;

import az.elvin.hotel.dao.DbHelper;
import az.elvin.hotel.dao.ExpensesDao;
import az.elvin.hotel.model.Expenses;
import az.elvin.hotel.model.ExpensesCategory;
import az.elvin.hotel.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExpensesDaoImpl implements ExpensesDao {
    @Override
    public List<Expenses> getExpensesList() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Expenses> expensesList = new ArrayList<Expenses>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT e.id_expenses,\n"
                + "         e.name,e.amount,e.date_insert,e.document,et.id_expenses_type id_expenses_type,et.expenses_type\n"
                + "    FROM expenses e\n"
                + "         INNER JOIN expenses_type et\n"
                + "            ON e.expenses_type_id = et.id_expenses_type\n"
                + "   WHERE e.active = 1\n"
                + "ORDER BY e.id_expenses";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Expenses expenses = new Expenses();
                    expenses.setId(rs.getLong("id_expenses"));
                    expenses.setName(rs.getString("name"));
                    expenses.setAmount(rs.getDouble("amount"));
                    expenses.setDataInsert(rs.getDate("date_insert"));
                    expenses.setDocument(rs.getString("document"));
                    ExpensesCategory expensesCategory = new ExpensesCategory();
                    expensesCategory.setId(rs.getLong("id_expenses_type"));
                    expensesCategory.setExpensesType(rs.getString("expenses_type"));
                    expenses.setExpensesCategory(expensesCategory);
                    expensesList.add(expenses);
                }

            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return expensesList;
    }

    @Override
    public List<Expenses> getExpensesListByExpensesCategoryId(long expensesCategoryId) throws Exception {
        List<Expenses> expensesList = new ArrayList<Expenses>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT DISTINCT e.id_expenses,\n"
                + "         e.name,e.amount,e.date_insert,e.document,et.id_expenses_type id_expenses_type,et.expenses_type\n"
                + "    FROM expenses e\n"
                + "         INNER JOIN expenses_type et\n"
                + "            ON e.expenses_type_id = et.id_expenses_type\n"
                + "   WHERE e.active = 1\n"
                + "AND et.id_expenses_type=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1,expensesCategoryId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Expenses expenses = new Expenses();
                    expenses.setId(rs.getLong("id_expenses"));
                    expenses.setName(rs.getString("name"));
                    expenses.setAmount(rs.getDouble("amount"));
                    expenses.setDataInsert(rs.getDate("date_insert"));
                    expenses.setDocument(rs.getString("document"));
                    expensesList.add(expenses);
                }

            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return expensesList;
    }

    @Override
    public boolean addExpenses(Expenses expenses) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO  expenses (name,amount,date_insert,expenses_type_id,document) " + " VALUES (?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, expenses.getName());
                ps.setDouble(2, expenses.getAmount());
                ps.setDate(3, new java.sql.Date(expenses.getDataInsert().getTime()));
                ps.setLong(4, expenses.getExpensesCategory().getId());
                ps.setString(5,expenses.getDocument());
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
    public Expenses getExpensesById(Long expensesId) throws Exception {
        Expenses expenses = new Expenses();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT e.id_expenses,\n"
                + "         e.name,e.amount,e.date_insert,e.document,et.id_expenses_type id_expenses_type,et.expenses_type\n"
                + "    FROM expenses e\n"
                + "         INNER JOIN expenses_type et\n"
                + "            ON e.expenses_type_id = et.id_expenses_type\n"
                + "   WHERE e.active = 1\n"
                + "AND e.id_expenses=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, expensesId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    expenses.setId(rs.getLong("id_expenses"));
                    expenses.setName(rs.getString("name"));
                    expenses.setAmount(rs.getDouble("amount"));
                    expenses.setDataInsert(rs.getDate("date_insert"));
                    expenses.setDocument(rs.getString("document"));
                    ExpensesCategory expensesCategory = new ExpensesCategory();
                    expensesCategory.setId(rs.getLong("id_expenses_type"));
                    expensesCategory.setExpensesType(rs.getString("expenses_type"));
                    expenses.setExpensesCategory(expensesCategory);
                } else {
                    expenses = null;
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return expenses;
    }

    @Override
    public boolean updateExpenses(Expenses expenses) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  expenses SET name=?,amount=?,date_insert=?,expenses_type_id=?,document=? WHERE id_expenses=?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, expenses.getName());
                ps.setDouble(2, expenses.getAmount());
                ps.setDate(3, new java.sql.Date(expenses.getDataInsert().getTime()));
                ps.setLong(4, expenses.getExpensesCategory().getId());
                ps.setString(5,expenses.getDocument());
                ps.setLong(6, expenses.getId());
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
    public boolean deleteExpenses(Long expensesId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  expenses SET active = 0 " + " WHERE id_expenses = ? ";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, expensesId);
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
    public List<Expenses> searchExpensesData(String keyword) throws Exception {
        List<Expenses> expensesList = new ArrayList<Expenses>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT e.id_expenses,\n"
                + "         e.name,e.amount,e.date_insert,et.id_expenses_type id_expenses_type,et.expenses_type\n"
                + "    FROM expenses e\n"
                + "         INNER JOIN expenses_type et\n"
                + "            ON e.expenses_type_id = et.id_expenses_type\n"
                + "   WHERE e.active = 1\n"
                + "AND ( LOWER(name) LIKE LOWER (?) OR LOWER(amount) LIKE LOWER (?) OR LOWER(date_insert) LIKE LOWER (?) OR LOWER(expenses_type) LIKE LOWER (?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Expenses expenses = new Expenses();
                    expenses.setId(rs.getLong("id_expenses"));
                    expenses.setName(rs.getString("name"));
                    expenses.setAmount(rs.getDouble("amount"));
                    expenses.setDataInsert(rs.getDate("date_insert"));
                    ExpensesCategory expensesCategory = new ExpensesCategory();
                    expensesCategory.setId(rs.getLong("id_expenses_type"));
                    expensesCategory.setExpensesType(rs.getString("expenses_type"));
                    expenses.setExpensesCategory(expensesCategory);
                    expensesList.add(expenses);
                }
            } else {
                System.out.println("Connection is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return expensesList;
    }
}
