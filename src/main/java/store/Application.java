package store;

import camp.nextstep.edu.missionutils.Console;
import store.domain.order.Order;
import store.domain.product.ProductRepository;
import store.domain.promotion.PromotionRepository;
import store.infrastructure.product.ProductFileReader;
import store.infrastructure.promotion.PromotionFileReader;
import store.service.OrderService;
import store.view.InputView;
import store.view.OutputView;

public class Application {

  public static void main(String[] args) {
    ProductRepository productRepository = new ProductFileReader();
    PromotionRepository promotionRepository = new PromotionFileReader();
    OutputView outputView = new OutputView(productRepository, promotionRepository);
    InputView inputView = new InputView();
    OrderService orderService = new OrderService(productRepository);

    System.out.println("---------- 환영인사 및 모든 상품 출력 ----------");
    outputView.displayWelcomeAndInventory();
    System.out.println();
    System.out.println("---------- 행사 출력 ----------");
    outputView.printPromotions();
    System.out.println("---------- 프로모션 출력 ----------");
    outputView.printPromotionProducts();
    inputView.readPurchaseItems();
    Order order = orderService.createOrder(Console.readLine());
    System.out.println(order);
  }
}
