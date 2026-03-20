package store.domain.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
  private final List<OrderItem> items = new ArrayList<>();

  public void addItem(OrderItem item) {
    items.add(item);
  }

  public List<OrderItem> getItems() {
    return Collections.unmodifiableList(items);
  }

  @Override
  public String toString() {
    return "Order{items=" + items + "}";
  }
}
