module lib.y.xplugin {
  requires lib.x;
  requires lib.y;

  provides org.example.x.spi.Plugin with org.example.y.plugin.YXPlugin;
}
