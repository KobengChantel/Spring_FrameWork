package tacos.web;

// Spring annotations and interfaces for configuring MVC
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 👉 This configuration class maps the root URL "/" directly to the "home" view without needing a dedicated controller method.

@Configuration  // Marks this class as a Spring configuration bean
public class WebConfig implements WebMvcConfigurer {

  // Adds simple view controllers that map URLs directly to views
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
    // Maps "/" URL to render "home.html" view
  }

}
