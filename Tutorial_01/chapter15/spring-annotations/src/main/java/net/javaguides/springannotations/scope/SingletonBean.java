package net.javaguides.springannotations.scope;

import org.springframework.stereotype.Component;

// This class demonstrates a singleton-scoped Spring bean, which is created only once per Spring application context.

@Component
// By default, Spring beans are singleton-scoped, so the @Scope annotation is optional here
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonBean {

    // Constructor prints a message when the single instance is created
    public SingletonBean() {
        System.out.println("SingletonBean..");
    }
}
