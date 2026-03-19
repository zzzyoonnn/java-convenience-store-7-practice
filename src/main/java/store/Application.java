package store;

import store.domain.product.ProductRepository;
import store.domain.promotion.PromotionRepository;
import store.infrastructure.product.ProductFileReader;
import store.infrastructure.promotion.PromotionFileReader;
import store.view.OutputView;

public class Application {

  public static void main(String[] args) {
    ProductRepository productRepository = new ProductFileReader();
    PromotionRepository promotionRepository = new PromotionFileReader();
    OutputView outputView = new OutputView(productRepository, promotionRepository);

    System.out.println("---------- 모든 상품 출력 ----------");
    outputView.printProducts();
    System.out.println();
    System.out.println("---------- 행사 출력 ----------");
    outputView.printPromotions();
    System.out.println("---------- 프로모션 출력 ----------");
    outputView.printPromotionProducts();
  }
}
