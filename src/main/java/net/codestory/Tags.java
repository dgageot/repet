package net.codestory;

import static net.codestory.http.misc.Fluent.*;

import java.util.*;

import net.codestory.http.templating.*;

public class Tags {
  private Map<String, List<String>> findAll() {
    return (Map<String, List<String>>) Site.get().getData().get("tags");
  }

  public long count(String component, String... tags) {
    return of(tags).count(of(findAll().get(component)).toSet()::contains);
  }
}
