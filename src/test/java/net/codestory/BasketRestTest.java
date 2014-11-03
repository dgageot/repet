package net.codestory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.codestory.http.Configuration;
import net.codestory.http.WebServer;
import net.codestory.http.injection.GuiceAdapter;
import net.codestory.http.routes.Routes;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static net.codestory.Server.ServerConfiguration;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasketRestTest {
  WebServer webServer = new WebServer(new ServerConfiguration()).startOnRandomPort();

  @Test
  public void query_basket() {
    given().port(webServer.port())
      .when().get("/basket?emails=david@devoxx.io,jeanlaurent@devoxx.io")
      .then().contentType("application/json").statusCode(200)
      .and().body("sum", equalTo(2000));
  }

  @Test
  public void query_scores() {
    BasketFactory basketFactory = mock(BasketFactory.class);
    Basket basket = basket(4, 3, 0, 3, 5, 2000);
    when(basketFactory.basket(asList("david@devoxx.io", "jeanlaurent@devoxx.io"))).thenReturn(basket);

    webServer.configure(new GuiceConfiguration(new ServerConfiguration()) {
      @Override
      protected void configure() {
        bind(BasketFactory.class).toInstance(basketFactory);
      }
    });

    given().port(webServer.port())
      .when().get("/basket?emails=david@devoxx.io,jeanlaurent@devoxx.io")
      .then().body("test", equalTo(4)).
      and().body("back", equalTo(3)).
      and().body("database", equalTo(0)).
      and().body("front", equalTo(3)).
      and().body("hipster", equalTo(5)).
      and().body("sum", equalTo(2000));
  }

  private static Basket basket(int test, int back, int database, int front, int hipster, int sum) {
    Basket basket = new Basket();
    basket.test = test;
    basket.back = back;
    basket.database = database;
    basket.front = front;
    basket.hipster = hipster;
    basket.sum = sum;
    return basket;
  }


  private static abstract class GuiceConfiguration extends AbstractModule implements Configuration {
    private final Configuration configuration;

    public GuiceConfiguration(Configuration configuration) {
      this.configuration = configuration;
    }

    @Override
    public final void configure(Routes routes) {
      Injector injector = Guice.createInjector(this);
      routes.setIocAdapter(new GuiceAdapter(injector));
      configuration.configure(routes);
    }
  }
}
