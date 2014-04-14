package net.codestory;

import static net.codestory.Server.*;
import static org.mockito.Mockito.*;

import net.codestory.http.*;
import net.codestory.selenium.*;

import org.junit.*;

import com.google.inject.*;

public class BasketSeleniumTest extends SeleniumTest {
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

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  @Test
  public void two_developers() {
    when(basketFactory.basket("david@gageot.net,mathilde@lemee.net")).thenReturn(new Basket(2, 2, 2, 2, 2, 1700));

    goTo("/");

    find("#clear").click();
    find("#David .btn-success").click();
    find("#Mathilde .btn-success").click();

    find("#basket .text-right").should().contain("1700");
  }
}
