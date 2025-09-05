package net.javaguides.springannotations.configurationproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.lang.String;
import java.util.HashMap;

// This class maps application properties from configuration files (application.properties or application.yml)
// to strongly-typed Java objects using @ConfigurationProperties.

@ConfigurationProperties(prefix = "app")
@Component
public class AppProperties {

    // Application name
    private String name;

    // Application description
    private String description;

    // Directory path for file uploads
    private String uploadDir;

    // Nested security configuration
    private Security security = new Security();

    // Inner class to hold security-related properties
    public static class Security {
        private String username;
        private String password;

        // List of user roles
        private List<String> roles = new ArrayList<>();

        // Flag to enable/disable security
        private boolean enabled;

        // Map of permissions (e.g., feature -> access level)
        private Map<String, String> permissions = new HashMap<>();

        // Getters and setters for permissions
        public Map<String, String> getPermissions() {
            return permissions;
        }

        public void setPermissions(Map<String, String> permissions) {
            this.permissions = permissions;
        }

        // Getters and setters for username
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        // Getters and setters for password
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        // Getters and setters for roles
        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        // Getters and setters for enabled flag
        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    // Getters and setters for application-level properties
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}
