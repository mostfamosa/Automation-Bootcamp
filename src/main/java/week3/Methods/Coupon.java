package Automation.src.week3.Methods;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Coupon {
    private int id;
    private Date expiryDate;
    private int value;

    public Coupon(int id, Date expiryDate, int value) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.value = value;
    }

    public Coupon() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyyy");
        return "Coupon{" +
                "id=" + id +
                ", expiryDate= " + ft.format(expiryDate) +
                ", value=" + value +"$"+
                '}';
    }
}
