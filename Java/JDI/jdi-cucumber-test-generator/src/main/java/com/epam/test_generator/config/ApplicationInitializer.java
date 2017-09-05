package com.epam.test_generator.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer{

    private final static String DISPATCHER = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx= new AnnotationConfigWebApplicationContext();

        ctx.register(WebConfig.class);

        servletContext.addListener(new ContextLoaderListener(ctx));
        ServletRegistration.Dynamic servlet =servletContext.addServlet(DISPATCHER,new DispatcherServlet(ctx));
        servlet.addMapping("/*");
    }
}