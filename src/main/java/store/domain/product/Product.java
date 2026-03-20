package store.domain.product;

import store.domain.stock.Stock;

public class Product {
  private final String name;
  private final int price;
  private final Stock stock;
  private final String promotion; // nullable

  public Product(String name, int price, Stock stock, String promotion) {
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.promotion = promotion;
  }

  public String getName() { return name; }
  public int getPrice() { return price; }

  public boolean hasPromotion() {
    return promotion != null;
  }

  public Stock getStock() { return stock; }

  public String getPromotion() { return promotion; }

  @Override
  public String toString() {
    return "Product{name='" + name + "', price=" + price
            + ", quantity=" + stock + ", promotion=" + promotion + "}";
  }
}
