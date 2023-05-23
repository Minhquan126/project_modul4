package ra.model.entity;

public class Cart {
    private int cartId;
    private int userId;
    private float total;

    public Cart() {
    }

    public Cart(int cartId, int userId, float total) {
        this.cartId = cartId;
        this.userId = userId;
        this.total = total;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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
}
