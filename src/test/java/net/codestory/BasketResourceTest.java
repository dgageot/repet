package net.codestory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketResourceTest {
  @Mock
  BasketFactory basketFactory;

  @InjectMocks
  BasketResource resource;

  @Test
  public void create_basket_for_emails() {
    Basket expectedBasket = new Basket();
    when(basketFactory.basket(asList("david@devoxx.io", "jeanlaurent@devoxx.io"))).thenReturn(expectedBasket);

    Basket basket = resource.basket("david@devoxx.io,jeanlaurent@devoxx.io");

    assertThat(basket).isSameAs(expectedBasket);
  }
}
