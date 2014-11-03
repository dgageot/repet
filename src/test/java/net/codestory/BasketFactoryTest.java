package net.codestory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BasketFactoryTest {
  @Mock
  Developers developers;
  @Mock
  Tags tags;

  @InjectMocks
  BasketFactory basketFactory;

  @Test
  public void empty_basket() {
    Basket basket = basketFactory.basket(emptyList());

    assertThat(basket.sum).isZero();
  }
}