package net.javaguides.springannotations.lazy;

import org.springframework.stereotype.Component;

// This class demonstrates eager initialization in Spring; the bean is created as soon as the application context loads.

@Component
public class EagerLoader {

    // Constructor prints a message when the bean is instantiated
    public EagerLoader() {
        System.out.println("EagerLoader ..");
    }
}
