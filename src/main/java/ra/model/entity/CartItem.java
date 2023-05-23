package ra.model.entity;

public class CartItem {
private int userId;
    private int productId;
    private String image;
    private String productName;
    private float price;
    private int quantity = 1;
    private float total;

    public CartItem() {
    }

    public CartItem(int userId, int productId, String image, String productName, float price) {
        this.userId = userId;
        this.productId = productId;
        this.image = image;
        this.productName = productName;
        this.price = price;
    }

    public CartItem(int userId, int productId, String image, String productName, float price, int quantity,float total) {
        this.userId = userId;
        this.productId = productId;
        this.image = image;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
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
