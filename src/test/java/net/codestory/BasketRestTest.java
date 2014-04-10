package net.codestory;

import static com.jayway.restassured.RestAssured.*;
import static net.codestory.Server.*;
import static org.hamcrest.CoreMatchers.*;

import net.codestory.http.*;

import org.junit.*;

import com.jayway.restassured.specification.*;

public class BasketRestTest {
  static WebServer webServer;

  @BeforeClass
  public static void startServer() {
    webServer = new WebServer(new ServerConfiguration()).startOnRandomPort();
  }

  @AfterClass
  public static void stopServer() {
    webServer.stop();
  }

  @Test
  public void one_developer() {
    when().get("/basket?emails=david@gageot.net").
        then().body("test", equalTo(2)).
        and().body("back", equalTo(1)).
        and().body("database", equalTo(0)).
        and().body("front", equalTo(1)).
        and().body("hipster", equalTo(3)).
        and().body("sum", equalTo(1000));
  }

  @Test
  public void two_developers() {
    when().get("/basket?emails=david@gageot.net,jeanlaurent@morlhon.net").
        then().body("test", equalTo(4)).
        and().body("back", equalTo(3)).
        and().body("database", equalTo(0)).
        and().body("front", equalTo(3)).
        and().body("hipster", equalTo(5)).
        and().body("sum", equalTo(2000));
  }

  private static RequestSpecification when() {
    return given().port(webServer.port()).when();
  }
}
