package org.example.app.xandy;

import org.example.x.X;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

  @Test
  void testPluginIsServiceLoaded() {
    X x = new X();
    Set<String> allPluginHi = x.allPluginHi();
    System.out.println("allPluginHi " + allPluginHi);

    assertThat(allPluginHi).contains("Y-says-YXPlugin");
  }
}
