package store.view;

import store.domain.product.Product;
import store.domain.product.ProductRepository;
import store.domain.promotion.PromotionRepository;

public class OutputView {

  private final ProductRepository productRepository;
  private final PromotionRepository promotionRepository;

  public OutputView(ProductRepository productRepository, PromotionRepository promotionRepository) {
    this.productRepository = productRepository;
    this.promotionRepository = promotionRepository;
  }

  public void displayWelcomeAndInventory() {
    printWelcomeMessage();
    printProducts();
  }

  public void printWelcomeMessage() {
    System.out.println("안녕하세요. W편의점입니다.");
    System.out.println("현재 보유하고 있는 상품입니다.\n");
  }

  public void printProducts() {
    productRepository.findAll().forEach(System.out::println);
  }

  public void printPromotions() {
    promotionRepository.findAll().forEach(System.out::println);
  }

  public void printPromotionProducts() {
    productRepository.findAll().stream()
            .filter(Product::hasPromotion)
            .forEach(System.out::println);
  }
}
