package store.domain.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  List<Product> findAll();

  Optional<Product> findByName(String name);

  List<Product> findAllByName(String name); // 프로모션/일반 동일 상품 복수 조회
}
