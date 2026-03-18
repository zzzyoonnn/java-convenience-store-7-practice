package store.domain.product;

public class Product {
  private final String name;
  private final int price;
  private int quantity;
  private final String promotion; // nullable

  public Product(String name, int price, int quantity, String promotion) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.promotion = promotion;
  }

  public String getName() { return name; }

  public boolean hasPromotion() {
    return promotion != null;
  }

  @Override
  public String toString() {
    return "Product{name='" + name + "', price=" + price
            + ", quantity=" + quantity + ", promotion=" + promotion + "}";
  }
}
