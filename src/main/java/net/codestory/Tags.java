package net.codestory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Tags {
  public long count(String category, String... developerTags) {
    List<String> tagsForCategory = findAll().get(category);

    return Stream.of(developerTags).filter(tag -> tagsForCategory.contains(tag)).count();
  }

  Map<String, List<String>> findAll() {
    try {
      return new ObjectMapper().readValue(Resources.getResource("tags.json"), new TypeReference<Map<String, List<String>>>() {
      });
    } catch (IOException e) {
      throw new RuntimeException("Unable to load tags list", e);
    }
  }
}
