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
### app.y only
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

We can split lib.y into 2 modules - lib.y and lib.y.xplugin. When the lib.y.xplugin is in a separate
jar we need to ensure that jar is included (as a maven dependency) for the plugin
to be included (which is what we want and ideally transparently).

This approach using 2 jars can open up the potential mistake where
the lib.y.xplugin jar is not included in the path.

We can avoid/automate/limit the chance of this by looking to always
include the lib.y.xplugin as a `maven` transitive dependency of lib.y.
This creates a build time cyclical dependency which we can get
around by having lib.y reference an older/existing version of the
lib.y.xplugin artifact.
