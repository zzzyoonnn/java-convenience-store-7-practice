package store.domain.product;

import store.domain.stock.Stock;

public class Product {
  private final String name;
  private final int price;
  private Stock stock;
  private final String promotion; // nullable

  public Product(String name, int price, Stock stock, String promotion) {
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.promotion = promotion;
  }

  public String getName() { return name; }

  public boolean hasPromotion() {
    return promotion != null;
  }

  public Stock getStock() { return stock; }

  @Override
  public String toString() {
    return "Product{name='" + name + "', price=" + price
            + ", quantity=" + stock + ", promotion=" + promotion + "}";
  }
}
