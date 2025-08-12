package com.rookies4.MySpringbootLab.Component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myprop")
public class MyPropProperties {
    private String name;
    private int port;

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
}

