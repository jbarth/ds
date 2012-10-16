package com.ubs.gtp.data.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.context.WebApplicationContext;

/**
 * Configures a mock {@link WebApplicationContext}.  Each test class (or parent class) using
 * {@link MockWebApplicationContextLoader} must be annotated with this.
 *
 * @see <a href="http://tedyoung.me/2011/02/14/spring-mvc-integration-testing-controllers/">Testing Controllers</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MockWebApplication {
    /**
     * The location of the webapp directory relative to your project.
     * For maven users, this is generally src/main/webapp (default).
     */
    String webapp() default "src/main/webapp";

    /**
     * The servlet name as defined in the web.xml.
     */
    String name();
}
