package store.infrastructure.promotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionRepository;

public class PromotionFileReader implements PromotionRepository {

  private static final String FILE_PATH = "/promotions.md";
  private static final String DELIMITER = ",";
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private final List<Promotion> promotions;

  public PromotionFileReader() {
    this.promotions = load();
  }

  @Override
  public List<Promotion> findAll() {
    return promotions;
  }

  @Override
  public Optional<Promotion> findByName(String name) {
    return promotions.stream().filter(promotion -> promotion.getName().equals(name)).findFirst();
  }

  public List<Promotion> load() {
    List<Promotion> result = new ArrayList<>();

    try (InputStream is = getClass().getResourceAsStream(FILE_PATH);
         BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

      String line;
      boolean isHeader = true;

      while ((line = reader.readLine()) != null) {
        if (isHeader) {       // 첫 줄(헤더) 건너뜀
          isHeader = false;
          continue;
        }
        result.add(parseLine(line));
      }

    } catch (IOException e) {
      throw new IllegalStateException("[ERROR] 프로모션 파일을 읽는 중 오류가 발생했습니다.", e);
    }

    return result;
  }

  private Promotion parseLine(String line) {
    String[] parts = line.split(DELIMITER);

    String name = parts[0].trim();
    int buy = Integer.parseInt(parts[1].trim());
    int get = Integer.parseInt(parts[2].trim());
    LocalDate startDate = LocalDate.parse(parts[3].trim(), FORMATTER);
    LocalDate endDate = LocalDate.parse(parts[4].trim(), FORMATTER);

    return new Promotion(name, buy, get, startDate, endDate);
  }
}
