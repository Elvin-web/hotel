package az.elvin.hotel.model;

import java.util.Date;

public class Expenses extends HotelModel {
    private ExpensesCategory expensesCategory;
    private String name;
    private Double amount;
    private Date dataInsert;
    private String document;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public ExpensesCategory getExpensesCategory() {
        return expensesCategory;
    }

    public void setExpensesCategory(ExpensesCategory expensesCategory) {
        this.expensesCategory = expensesCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDataInsert() {
        return dataInsert;
    }

    public void setDataInsert(Date dataInsert) {
        this.dataInsert = dataInsert;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "expensesCategory=" + expensesCategory +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", dataInsert=" + dataInsert +
                ", document='" + document + '\'' +
                '}';
    }
}
