package net.javaguides.springannotations.lazy;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;

// This class demonstrates lazy initialization in Spring; the bean is created only when it is first requested.

@Component
@Lazy  // Marks this bean to be loaded on demand rather than at application startup
public class LazyLoader {

    // Constructor prints a message when the bean is instantiated
    public LazyLoader() {
        System.out.println("LazyLoader ..");
    }
}
