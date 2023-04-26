package org.example.app.yonly;

import org.example.y.Y;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

  @Test
  void testDoesNotFailModuleResolution() {
    Y y = new Y();
    assertThat(y.doGoodStuff()).isEqualTo("Y-is-Awesome!!");
  }
}
