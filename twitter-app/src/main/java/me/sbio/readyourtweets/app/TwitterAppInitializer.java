package me.sbio.readyourtweets.app;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class TwitterAppInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_PATH = "/";
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(TwitterAppConfig.class);
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(context));
        dispatcherServlet.addMapping(DISPATCHER_SERVLET_PATH);
        dispatcherServlet.setLoadOnStartup(1);

    }
}
