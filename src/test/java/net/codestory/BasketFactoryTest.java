package net.codestory;

import static org.assertj.core.api.Assertions.*;

import org.junit.*;

public class BasketFactoryTest {
  @Test
  public void one_developer() {
    Basket basket = new BasketFactory().basket("david@gageot.net");

    assertThat(basket.test).isEqualTo(2);
    assertThat(basket.back).isEqualTo(1);
    assertThat(basket.database).isEqualTo(0);
    assertThat(basket.front).isEqualTo(1);
    assertThat(basket.hipster).isEqualTo(3);
    assertThat(basket.sum).isEqualTo(1000);
  }

  @Test
  public void two_developer() {
    Basket basket = new BasketFactory().basket("david@gageot.net,jeanlaurent@morlhon.net");

    assertThat(basket.test).isEqualTo(4);
    assertThat(basket.back).isEqualTo(3);
    assertThat(basket.database).isEqualTo(0);
    assertThat(basket.front).isEqualTo(3);
    assertThat(basket.hipster).isEqualTo(5);
    assertThat(basket.sum).isEqualTo(2000);
  }
}
