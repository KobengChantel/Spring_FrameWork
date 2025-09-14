package tacos.web;

// Spring MVC configuration classes
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 👉 This configuration maps the root URL ("/") directly to the "home" view without needing a controller method.

@Configuration  // Marks this class as a Spring configuration class
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // Maps the "/" URL to the "home" view template
    registry.addViewController("/").setViewName("home");
  }

}
