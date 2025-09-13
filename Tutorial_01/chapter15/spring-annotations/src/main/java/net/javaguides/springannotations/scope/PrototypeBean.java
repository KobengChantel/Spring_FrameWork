package net.javaguides.springannotations.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// This class demonstrates a prototype-scoped Spring bean, which creates a new instance every time it is requested.

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Marks this bean as prototype-scoped
public class PrototypeBean {

    // Constructor prints a message whenever a new instance is created
    public PrototypeBean() {
        System.out.println("PrototypeBean");
    }
}
