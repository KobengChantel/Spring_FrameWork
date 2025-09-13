package net.javaguides.springannotations.lazy.propertysource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

// This class is a Spring configuration that loads external property files into the application context.

@Configuration
// Multiple property files can be loaded using @PropertySources
@PropertySources({
        @PropertySource("classpath:mail.properties"),     // Loads mail-related properties
        @PropertySource("classpath:messages.properties")  // Loads messages-related properties
})
public class SpringConfig {
    // No methods required — this class simply registers the property sources.
}
