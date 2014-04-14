package net.codestory;

import static com.google.inject.Guice.*;

import net.codestory.http.*;
import net.codestory.http.injection.*;
import net.codestory.http.routes.*;

import com.google.inject.*;

public class Server {
  public static void main(String[] args) {
    new WebServer(new ServerConfiguration()).start();
  }

  public static class ServerConfiguration implements Configuration {
    private final Injector injector;

    public ServerConfiguration(Module... modules) {
      this.injector = createInjector(modules);
    }

    @Override
    public void configure(Routes routes) {
      routes
          .setIocAdapter(new GuiceAdapter(injector))
          .get("/basket?emails=:emails", (context, emails) -> context.getBean(BasketFactory.class).basket(emails));
    }
  }
}
