# skeleton-optional-service

### lib.x
```java
module lib.x {
  exports org.example.x;
  exports org.example.x.spi;

  uses org.example.x.spi.Plugin;
}
```


### lib.y
```java
module lib.y {
  exports org.example.y;
}
```

### lib.y.xplugin
```java
module lib.y.xplugin {
  requires lib.x;
  requires lib.y;

  provides org.example.x.spi.Plugin with org.example.y.plugin.YXPlugin;
}
```

------
### app.y only
```java
module app.y.only {
  requires lib.y;
}
```
- Does NOT fail module resolution even when lib.y.xplugin is in the path

### app x and y
```java
module app.xy {
  requires lib.x;
  requires lib.y;
}
```
- Does not need `requires lib.y.xplugin;`
- Only needs the lib.y.xplugin artifact to be in the path for the service to be found and loaded.


-------
### In summary

Some future version of Java that support 2 module definitions in a single
jar artifact would resolve the current issue with supporting optional services.


### Workaround based on this approach

We can split lib.y into 2 modules and 2 artifacts today - lib.y and lib.y.xplugin. When the lib.y.xplugin is in a separate
jar we need to ensure that jar is included (as a maven dependency) for the plugin
to be included which is what we want and ideally transparently to the users of lib.y.

This approach using 2 jars _can_ open up the potential mistake where
the lib.y.xplugin jar is not included in the path.

We can avoid/workaround/limit the chance of this by either:
1. Using a maven composite to combine the 2 dependencies into 1
2. Include the lib.y.xplugin as a `maven` transitive dependency of lib.y.
This creates a build time cyclical dependency which we can get
around by having lib.y reference an older/existing version of the
lib.y.xplugin artifact.

This option 2 is very close to in effect simulating a single jar with
2 module definitions.


### Alternative workaround

Not use ServiceLoader. The benefits of this is that it is easy, works with
both classpath and module-path. The downside is that plugin implementations
must be in an exported package.
