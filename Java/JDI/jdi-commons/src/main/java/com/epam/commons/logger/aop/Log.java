package com.epam.commons.logger.aop;

import java.lang.annotation.*;

/**
 * Created by Roman_Iovlev on 7/30/2017.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    String value() default "";

}
