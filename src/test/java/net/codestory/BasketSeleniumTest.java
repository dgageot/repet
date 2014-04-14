package net.codestory;

import static net.codestory.Server.*;

import net.codestory.http.*;
import net.codestory.selenium.*;

import org.junit.*;

public class BasketSeleniumTest extends SeleniumTest {
  static WebServer webServer;

  @BeforeClass
  public static void startServer() {
    webServer = new WebServer(new ServerConfiguration()).startOnRandomPort();
  }

  @AfterClass
  public static void stopServer() {
    webServer.stop();
  }

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  @Test
  public void two_developers() {
    goTo("/");

    find("#clear").click();
    find("#David .btn-success").click();
    find("#Mathilde .btn-success").click();

    find("#basket .text-right").should().contain("1700");
  }
}
