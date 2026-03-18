package store.domain.promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository {

  List<Promotion> findAll();

  Optional<Promotion> findByName(String name);
}
