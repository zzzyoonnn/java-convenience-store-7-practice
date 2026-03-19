package store.domain.promotion;

import java.time.LocalDate;

public class Promotion {
  private final String name;
  private final int buy;
  private final int get;
  private final LocalDate startDate;
  private final LocalDate endDate;

  public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
    this.name = name;
    this.buy = buy;
    this.get = get;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getName() { return name; }

  public boolean isActive(LocalDate date) {
    return !date.isBefore(startDate) && !date.isAfter(endDate);
  }

  // 프로모션 적용 가능 여부 (날짜 + 재고)
  public boolean isApplicable(LocalDate date, int promotionStock) {
    return isActive(date) && promotionStock >= buy;
  }

  // 증정 수량: 구매 수량 중 프로모션 단위(buy+get)로 나눈 몫
  public int calculateFreeQuantity(int quantity) {
    return (quantity / (buy + get)) * get;
  }

  // 프로모션 재고로 처리할 수량
  public int calculatePromotionQuantity(int quantity) {
    return (quantity / (buy + get)) * (buy + get);
  }

  // 프로모션 적용 후 일반 재고에서 차감할 수량
  public int calculateNormalQuantity(int quantity) {
    return quantity - calculatePromotionQuantity(quantity);
  }

  @Override
  public String toString() {
    return "Promotion{name='" + name + "', buy=" + buy + ", get=" + get
            + ", startDate=" + startDate + ", endDate=" + endDate + "}";
  }
}
