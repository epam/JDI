package com.epam.test_generator.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

    private final static String DISPATCHER = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx= new AnnotationConfigWebApplicationContext();

        ctx.register(WebConfig.class);

        ServletRegistration.Dynamic servlet =servletContext.addServlet(DISPATCHER,new DispatcherServlet(ctx));
        servlet.addMapping("/*");
    }
}