package net.codestory;

import net.codestory.http.convert.*;
import net.codestory.http.templating.*;

public class Developers {
  public Developer[] findAll() {
    return TypeConvert.convertValue(Site.get().getData().get("developers"), Developer[].class);
  }
}
