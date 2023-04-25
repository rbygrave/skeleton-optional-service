package org.example.app.xandy;

import org.example.x.X;

import java.util.Set;

public class Main {
  public static void main(String[] args) {

    X x = new X();
    Set<String> allPluginHi = x.allPluginHi();
    System.out.println("allPluginHi " + allPluginHi);

    assert allPluginHi.contains("Y-says-YXPlugin");
  }
}
