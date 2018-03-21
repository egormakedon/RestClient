package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public final class ServerhostProperties {
    private static final String FATAL_NAME = "FATAL";
    private static final Marker FATAL = MarkerFactory.getMarker(FATAL_NAME);
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerhostProperties.class);

    private static final ServerhostProperties INSTANCE = new ServerhostProperties();
    private ServerhostProperties() {
        set();
    }
    public static ServerhostProperties getInstance() {
        return INSTANCE;
    }

    private static final String SERVERHOST_PROPERTY_PATH = "/property/serverhost.properties";

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    private void set() {
        URL url = this.getClass().getResource(SERVERHOST_PROPERTY_PATH);
        if (url == null) {
            LOGGER.error(FATAL, "serverhost property hasn't found");
            throw new RuntimeException();
        }

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(url.toURI())));
        } catch (URISyntaxException | IOException e) {
            LOGGER.error("", e);
        }

        host = properties.getProperty("serverhost.ip");
        port = Integer.parseInt(properties.getProperty("serverhost.port"));
    }
}
