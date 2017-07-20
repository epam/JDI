package com.epam.jdi.cucumber.stepdefs.vtb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created: 16-May-17
 *
 * @author <a href="mailto:Denis_Malenkov@epam.com">Denis Malenkov</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ExampleUseStep {
    String value();
}
