package net.codestory;

import com.google.common.base.Splitter;
import net.codestory.http.annotations.Get;

import javax.inject.Inject;
import java.util.List;

public class BasketResource {
  private final BasketFactory basketFactory;

  @Inject
  public BasketResource(BasketFactory basketFactory) {
    this.basketFactory = basketFactory;
  }

  @Get("/basket?emails=:emails")
  public Basket basket(String emailList) {
    List<String> emails = Splitter.on(",").omitEmptyStrings().splitToList(emailList);

    return basketFactory.basket(emails);
  }
}
