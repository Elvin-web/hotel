package az.elvin.hotel.model;

public class ExpensesCategory extends HotelModel{
    private String expensesType;

    public String getExpensesType() {
        return expensesType;
    }

    public void setExpensesType(String expensesType) {
        this.expensesType = expensesType;
    }

    @Override
    public String toString() {
        return "ExpensesCategory{" + "expensesType=" + expensesType + '}';
    }
}
