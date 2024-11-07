package net.javaguides.springannotations.scope;

import org.springframework.stereotype.Component;

@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonBean {

    public SingletonBean(){
        System.out.println("SingletonBean..");
    }
}
