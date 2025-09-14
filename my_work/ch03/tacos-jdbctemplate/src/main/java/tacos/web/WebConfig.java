package tacos.web;

// Spring annotations and interfaces for configuring MVC
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 👉 This class configures Spring MVC to map the root URL "/" to the "home" view without requiring a controller method.

@Configuration  // Marks this class as a configuration bean
public class WebConfig implements WebMvcConfigurer {
  // Implements WebMvcConfigurer to customize Spring MVC configuration

  // Adds simple view controllers that map URLs directly to views
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
    // Maps the root URL "/" to the view named "home.html"
  }

}
