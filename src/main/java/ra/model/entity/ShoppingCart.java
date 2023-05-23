package ra.model.entity;

public class ShoppingCart {
    private String image;
    private String productName;
    private float price;
    private int quantity;
private float total;

    public ShoppingCart() {
    }

    public ShoppingCart(String image, String productName, float price, int quantity, float total) {
        this.image = image;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
