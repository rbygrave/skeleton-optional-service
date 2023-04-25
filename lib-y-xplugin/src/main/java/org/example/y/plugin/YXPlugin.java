package org.example.y.plugin;


import org.example.x.spi.Plugin;
import org.example.y.Y;

public class YXPlugin implements Plugin {

  private final Y y = new Y();

  @Override
  public String pluginSaysHi() {
    return y.hi("YXPlugin");
  }
}
