package store.domain.order;

public class OrderItem {
  private final String productName;
  private final int price;
  private final int quantity;

  public OrderItem(String productName, int price, int quantity) {
    this.productName = productName;
    this.price = price;
    this.quantity = quantity;
  }

  public String getProductName() { return productName; }
  public int getPrice() { return price; }
  public int getQuantity() { return quantity; }
  public int getTotalPrice() { return price * quantity; }

  @Override
  public String toString() {
    return "OrderItem{productName='" + productName + "', price=" + price + ", quantity=" + quantity + "}";
  }
}
