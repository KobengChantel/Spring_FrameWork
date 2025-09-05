package net.javaguides.springannotations.configurationproperties;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// This class demonstrates how to use the AppProperties class by autowiring it and printing its values.

@Component
public class AppPropertiesDemo {

    // Inject AppProperties bean to access application configuration values
    @Autowired
    private AppProperties appProperties;

    // Method to display values loaded from application.properties or application.yml
    public void display() {
        // Print general application properties
        System.out.println(appProperties.getName());
        System.out.println(appProperties.getDescription());
        System.out.println(appProperties.getUploadDir());

        // Print nested security properties
        System.out.println(appProperties.getSecurity().getUsername());
        System.out.println(appProperties.getSecurity().getPassword());

        // Direct access to roles (list of user roles)
        System.out.println(appProperties.getSecurity().roles);

        // Print whether security is enabled
        System.out.println(appProperties.getSecurity().isEnabled());

        // Print map of permissions
        System.out.println(appProperties.getSecurity().getPermissions());
    }
}
