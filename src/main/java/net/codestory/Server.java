package net.codestory;

import net.codestory.http.*;

public class Server {
  public static void main(String[] args) {
    new WebServer(routes -> routes
        .get("/basket?emails=:emails", (context, emails) -> new BasketFactory().basket(emails))
    ).start();
  }
}
