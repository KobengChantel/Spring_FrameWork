package net.javaguides.springannotations.lazy.propertysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

// This class demonstrates how to access application properties from the Environment in Spring.

@Component
public class PropertySourceDemo {

    // Spring Environment object provides access to properties from application.properties or application.yml
    @Autowired
    private Environment environment;

    // These fields could be injected directly using @Value, but are left commented for demonstration
    // @Value("${gmail.host}")
    private String host;

    // @Value("${gmail.email}")
    private String email;

    // @Value("${gmail.password}")
    private String password;

    // @Value("${app.name}")
    private String appName;

    // @Value("${app.description}")
    private String appDesc;

    // Get property "gmail.host" from application.properties or environment variables
    public String getHost() {
        return environment.getProperty("gmail.host");
    }

    // Get property "gmail.email"
    public String getEmail() {
        return environment.getProperty("gmail.email");
    }

    // Get property "gmail.password"
    public String getPassword() {
        return environment.getProperty("gmail.password");
    }

    // Get property "app.name"
    public String getAppName() {
        return environment.getProperty("app.name");
    }

    // Get property "app.description"
    public String getAppDesc() {
        return environment.getProperty("app.description");
    }
}
