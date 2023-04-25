package org.example.x;

import org.example.x.spi.Plugin;

import java.util.LinkedHashSet;
import java.util.ServiceLoader;
import java.util.Set;

public class X {


    public Set<String> allPluginHi() {
        Set<String> allHi = new LinkedHashSet<>();
        for (Plugin plugin : ServiceLoader.load(Plugin.class)) {
            allHi.add(plugin.pluginSaysHi());
        }
        return allHi;
    }
}
