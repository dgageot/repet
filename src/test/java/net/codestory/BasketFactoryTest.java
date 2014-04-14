package net.codestory;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;

public class BasketFactoryTest {
  Developers developers = mock(Developers.class);
  Tags tags = mock(Tags.class);

  BasketFactory basketFactory = new BasketFactory(developers, tags);

  @Test
  public void one_developer() {
    String[] tagsDavid = {"TEST1", "TEST2", "BACK1", "FRONT1"};

    when(tags.count("test", tagsDavid)).thenReturn(2L);
    when(tags.count("back", tagsDavid)).thenReturn(1L);
    when(tags.count("database", tagsDavid)).thenReturn(0L);
    when(tags.count("front", tagsDavid)).thenReturn(2L);
    when(tags.count("hipster", tagsDavid)).thenReturn(3L);

    when(developers.findAll()).thenReturn(new Developer[]{
        developer("david", 1000, tagsDavid)
    });

    Basket basket = basketFactory.basket("david");

    assertThat(basket.test).isEqualTo(2);
    assertThat(basket.back).isEqualTo(1);
    assertThat(basket.database).isEqualTo(0);
    assertThat(basket.front).isEqualTo(2);
    assertThat(basket.hipster).isEqualTo(3);
    assertThat(basket.sum).isEqualTo(1000);
  }

  @Test
  public void two_developers() {
    String[] tagsDavid = {"TEST1", "TEST2", "BACK1", "FRONT1"};
    String[] tagsJL = {"TEST1", "TEST2", "BACK1", "DATABASE1", "FRONT1", "FRONT2"};

    when(tags.count("test", tagsDavid)).thenReturn(2L);
    when(tags.count("back", tagsDavid)).thenReturn(1L);
    when(tags.count("database", tagsDavid)).thenReturn(0L);
    when(tags.count("front", tagsDavid)).thenReturn(1L);
    when(tags.count("hipster", tagsDavid)).thenReturn(3L);

    when(tags.count("test", tagsJL)).thenReturn(2L);
    when(tags.count("back", tagsJL)).thenReturn(1L);
    when(tags.count("database", tagsJL)).thenReturn(1L);
    when(tags.count("front", tagsJL)).thenReturn(2L);
    when(tags.count("hipster", tagsJL)).thenReturn(3L);

    when(developers.findAll()).thenReturn(new Developer[]{
        developer("david", 1000, tagsDavid),
        developer("jl", 1100, tagsJL)
    });

    Basket basket = basketFactory.basket("david,jl");

    assertThat(basket.test).isEqualTo(4);
    assertThat(basket.back).isEqualTo(2);
    assertThat(basket.database).isEqualTo(1);
    assertThat(basket.front).isEqualTo(3);
    assertThat(basket.hipster).isEqualTo(6);
    assertThat(basket.sum).isEqualTo(2100);
  }

  static Developer developer(String email, int price, String... tags) {
    Developer dev = new Developer();
    dev.email = email;
    dev.price = price;
    dev.tags = tags;
    return dev;
  }
}
