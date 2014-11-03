package net.codestory;

import net.codestory.http.annotations.Get;
import net.codestory.http.templating.Model;

import javax.inject.Inject;

public class IndexResource {
  private final Developers developers;

  @Inject
  public IndexResource(Developers developers) {
    this.developers = developers;
  }

  @Get("/")
  public Model index() {
    return Model.of("developers", developers.findAll());
  }
}
