package net.codestory;

import static net.codestory.Server.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import net.codestory.http.*;

import org.junit.*;

import com.google.inject.*;
import com.jayway.restassured.*;

public class BasketRestTest {
  static BasketFactory basketFactory = mock(BasketFactory.class);

  static WebServer webServer;

  @BeforeClass
  public static void startServer() {
    webServer = new WebServer(new ServerConfiguration(new AbstractModule() {
      @Override
      protected void configure() {
        bind(BasketFactory.class).toInstance(basketFactory);
      }
    })).startOnRandomPort();
  }

  @Test
  public void two_developers() {
    when(basketFactory.basket("david@gageot.net,jeanlaurent@morlhon.net")).thenReturn(new Basket(4, 3, 0, 3, 5, 2000));

    RestAssured
        .given().port(webServer.port())
        .when().get("/basket?emails=david@gageot.net,jeanlaurent@morlhon.net").
        then().body("test", equalTo(4)).
        and().body("back", equalTo(3)).
        and().body("database", equalTo(0)).
        and().body("front", equalTo(3)).
        and().body("hipster", equalTo(5)).
        and().body("sum", equalTo(2000));
  }
}
