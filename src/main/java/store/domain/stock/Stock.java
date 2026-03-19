package store.domain.stock;

public class Stock {
  private int normalStock;
  private int promotionStock;

  public Stock(int normalStock, int promotionStock) {
    this.normalStock = normalStock;
    this.promotionStock = promotionStock;
  }

  public int getNormalStock() { return normalStock; }
  public int getPromotionStock() { return promotionStock; }

  public int getTotalStock() {
    return normalStock + promotionStock;
  }

  public boolean hasEnoughStock(int quantity) {
    return getTotalStock() >= quantity;
  }

  public void merge(Stock other) {
    this.normalStock += other.normalStock;
    this.promotionStock += other.promotionStock;
  }

  // 프로모션 재고 우선 차감, 부족하면 일반 재고에서 차감
  public void deduct(int quantity) {
    validateEnoughStock(quantity);
    int fromPromotion = Math.min(promotionStock, quantity);
    promotionStock -= fromPromotion;
    normalStock -= (quantity - fromPromotion);
  }

  public void deductPromotion(int quantity) {
    if (promotionStock < quantity) {
      throw new IllegalArgumentException("프로모션 재고가 부족합니다.");
    }
    promotionStock -= quantity;
  }

  public void deductNormal(int quantity) {
    if (normalStock < quantity) {
      throw new IllegalArgumentException("일반 재고가 부족합니다.");
    }
    normalStock -= quantity;
  }

  private void validateEnoughStock(int quantity) {
    if (!hasEnoughStock(quantity)) {
      throw new IllegalArgumentException("재고가 부족합니다.");
    }
  }

  @Override
  public String toString() {
    return "Stock{normalStock=" + normalStock + ", promotionStock=" + promotionStock + "}";
  }
}
