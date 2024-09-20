package ru.doom.wad.logic.app;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.jar.Manifest;

@Component
public class VersionHelper {

    private String version = "";

    @PostConstruct
    public void init() {
        ClassLoader cl = getClass().getClassLoader();
        try {
            URL url = cl.getResource("META-INF/MANIFEST.MF");
            if (url == null) {
                return;
            }
            Manifest manifest = new Manifest(url.openStream());
            String value = manifest.getMainAttributes().getValue("Implementation-Version");
            if (value != null) {
                version = value;
            }
        } catch (IOException E) {
            // ignore
        }
    }

    public String getVersion() {
        return version;
    }
}
