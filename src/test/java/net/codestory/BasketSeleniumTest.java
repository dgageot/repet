package net.codestory;

import static net.codestory.Server.*;

import net.codestory.http.*;
import net.codestory.selenium.*;

import org.junit.*;

public class BasketSeleniumTest extends SeleniumTest {
  WebServer webServer = new WebServer(new ServerConfiguration()).startOnRandomPort();

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

  @Test
  public void one_developer() {
    goTo("/");

    find("#clear").click();
    find("#David .btn-success").click();

    find("#basket .box.test:not(.ng-hide)").should().haveSize(1);
    find("#basket .box.back:not(.ng-hide)").should().haveSize(1);
    find("#basket .box.database:not(.ng-hide)").should().beEmpty();
    find("#basket .box.front:not(.ng-hide)").should().haveSize(2);
    find("#basket .box.hipster:not(.ng-hide)").should().haveSize(1);
  }
}
