package store.service;

import java.util.LinkedHashMap;
import java.util.Map;
import store.domain.order.Order;
import store.domain.order.OrderItem;
import store.domain.product.Product;
import store.domain.product.ProductRepository;

public class OrderService {
  private final ProductRepository productRepository;

  public OrderService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }


  public Order createOrder(String input) {
    Map<String, Integer> orderRequests = parseInput(input);

    // 1단계: 전체 재고 검증
    for (Map.Entry<String, Integer> entry : orderRequests.entrySet()) {
      Product product = productRepository.findByName(entry.getKey())
              .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다."));

      if (!product.getStock().hasEnoughStock(entry.getValue())) {
        throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다.");
      }
    }

    // 2단계: 차감 + OrderItem 생성
    Order order = new Order();
    for (Map.Entry<String, Integer> entry : orderRequests.entrySet()) {
      Product product = productRepository.findByName(entry.getKey()).get();
      product.getStock().deduct(entry.getValue());
      order.addItem(new OrderItem(product.getName(), product.getPrice(), entry.getValue()));
    }

    return order;
  }

  private Map<String, Integer> parseInput(String input) {
    Map<String, Integer> result = new LinkedHashMap<>();

    for (String token : input.split(",")) {
      String clean = token.trim().replaceAll("[\\[\\]]", "");
      String[] parts = clean.split("-");
      result.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
    }

    return result;
  }
}
