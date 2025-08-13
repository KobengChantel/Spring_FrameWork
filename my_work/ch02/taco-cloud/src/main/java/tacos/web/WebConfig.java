package tacos.web; // Declares that this class belongs to the 'tacos.web' package

import org.springframework.context.annotation.Configuration; // Marks this class as a Spring configuration class
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry; // Allows registering simple view controllers without logic
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Interface for customizing Spring MVC configuration

@Configuration // Indicates that this class provides Spring MVC configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // Maps the root URL ("/") directly to the "home" view without a controller method
    registry.addViewController("/").setViewName("home");
  }
}
