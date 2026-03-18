package store.infrastructure.product;

import store.domain.product.Product;
import store.domain.product.ProductRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductFileReader implements ProductRepository {

  private static final String FILE_PATH = "/products.md";
  private static final String DELIMITER = ",";
  private static final String NULL_VALUE = "null";

  private final List<Product> products;

  public ProductFileReader() {
    this.products = load();
  }

  @Override
  public List<Product> findAll() {
    return products;
  }

  @Override
  public Optional<Product> findByName(String name) {
    return products.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst();
  }

  @Override
  public List<Product> findAllByName(String name) {
    return products.stream()
            .filter(p -> p.getName().equals(name))
            .collect(Collectors.toList());
  }

  private List<Product> load() {
    List<Product> result = new ArrayList<>();

    try (InputStream is = getClass().getResourceAsStream(FILE_PATH);
         BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

      String line;
      boolean isHeader = true;

      while ((line = reader.readLine()) != null) {
        if (isHeader) {
          isHeader = false;
          continue;
        }
        result.add(parseLine(line));
      }

    } catch (IOException e) {
      throw new IllegalStateException("[ERROR] 상품 파일을 읽는 중 오류가 발생했습니다.", e);
    }

    return result;
  }

  private Product parseLine(String line) {
    String[] parts = line.split(DELIMITER);

    String name = parts[0].trim();
    int price = Integer.parseInt(parts[1].trim());
    int quantity = Integer.parseInt(parts[2].trim());
    String promotion = parsePromotion(parts[3].trim());

    return new Product(name, price, quantity, promotion);
  }

  private String parsePromotion(String value) {
    if (NULL_VALUE.equals(value)) {
      return null;
    }
    return value;
  }
}
