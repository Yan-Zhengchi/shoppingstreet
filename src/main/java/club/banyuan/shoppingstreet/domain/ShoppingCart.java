package club.banyuan.shoppingstreet.domain;

public class ShoppingCart {
    public Integer productId;
    public Integer count;
    public Double cost;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "productId=" + productId +
                ", count=" + count +
                ", cost=" + cost +
                '}';
    }
}
