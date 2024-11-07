package net.javaguides.springannotations.lazy;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;

@Component
//loading spring bean of this cass on demand
@Lazy
public class LazyLoader {

    public LazyLoader(){
        System.out.println("LazyLoader ..");
    }
}
