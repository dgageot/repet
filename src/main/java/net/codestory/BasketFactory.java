package net.codestory;

import static net.codestory.http.misc.Fluent.*;

import java.util.*;

import net.codestory.http.convert.*;
import net.codestory.http.templating.*;

public class BasketFactory {
  private final Developer[] allDevelopers;
  private final Map<String, List<String>> allTags;

  public BasketFactory() {
    this.allDevelopers = TypeConvert.convertValue(Site.get().getData().get("developers"), Developer[].class);
    this.allTags = (Map<String, List<String>>) Site.get().getData().get("tags");
  }

  public Basket basket(String emails) {
    Basket basket = new Basket();

    for (Developer developer : findDeveloper(emails)) {
      String[] tags = developer.tags;

      basket.test += count("test", tags);
      basket.back += count("back", tags);
      basket.database += count("database", tags);
      basket.front += count("front", tags);
      basket.hipster += count("hipster", tags);
      basket.sum += developer.price;
    }

    return basket;
  }

  private long count(String component, String[] tags) {
    return of(tags).count(of(allTags.get(component)).toSet()::contains);
  }

  private Iterable<Developer> findDeveloper(String emails) {
    return split(emails, ",").map(of(allDevelopers).uniqueIndex(dev -> dev.email)::get).notNulls();
  }
}
