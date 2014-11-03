package net.codestory;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TagsTest {
  Tags tags = new Tags();

  @Test
  public void load_tags() {
    Map<String, List<String>> allTags = tags.findAll();

    assertThat(allTags).hasSize(5);
  }

  @Test
  public void count_tags_for_category() {
    long count = tags.count("test", "TAG1", "TAG2", "Tdd");

    assertThat(count).isEqualTo(1);
  }
}