package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderShow {
    private int orderId;
    private int userId;
    private int productId;
    private String productName;
    private float price;
    private int quantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date dateBuy;

    public OrderShow() {
    }

    public OrderShow(int orderId, int userId, int productId, String productName, float price, int quantity, Date dateBuy) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.dateBuy = dateBuy;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }
}
