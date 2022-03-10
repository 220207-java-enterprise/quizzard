package com.revature.quizzard.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.quizzard.config.AppConfig;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.TokenService;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.servlets.AuthServlet;
import com.revature.quizzard.servlets.UserServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    private static Logger logger = LogManager.getLogger(ContextLoaderListener.class);
    // Spring ApplicationContext (IoC container)
    ApplicationContext appContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("Initializing Quizzard web application");

        appContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserServlet userServlet = appContext.getBean(UserServlet.class);
        AuthServlet authServlet = appContext.getBean(AuthServlet.class);

        // Programmatic Servlet Registration
        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("Shutting down Quizzard web application");
        
        ((ConfigurableWebApplicationContext) this.appContext).close();
    }

}
