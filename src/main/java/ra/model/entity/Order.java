package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private float total;
    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date date;
    private boolean status;

    public Order() {
    }

    public Order(int orderId, int userId, float total, Date date, boolean status) {
        this.orderId = orderId;
        this.userId = userId;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
