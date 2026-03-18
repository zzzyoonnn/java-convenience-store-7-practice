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

  @Override
  public String toString() {
    return "Promotion{name='" + name + "', buy=" + buy + ", get=" + get
            + ", startDate=" + startDate + ", endDate=" + endDate + "}";
  }
}
