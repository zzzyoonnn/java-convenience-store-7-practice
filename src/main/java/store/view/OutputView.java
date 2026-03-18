package store.view;

import store.domain.product.ProductRepository;
import store.domain.promotion.PromotionRepository;

public class OutputView {

  private final ProductRepository productRepository;
  private final PromotionRepository promotionRepository;

  public OutputView(ProductRepository productRepository, PromotionRepository promotionRepository) {
    this.productRepository = productRepository;
    this.promotionRepository = promotionRepository;
  }

  public void printProducts() {
    productRepository.findAll().forEach(System.out::println);
  }

  public void printPromotions() {
    promotionRepository.findAll().forEach(System.out::println);
  }
}
