package cs544;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // Create the Spring 'root' application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(Config.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));
        
		ServletRegistration.Dynamic appServlet = container.addServlet("mvc",
				new DispatcherServlet(new GenericWebApplicationContext()));
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");

//        FilterRegistration.Dynamic openInView = container.addFilter("OpenInView", new OpenSessionInViewFilter());
        FilterRegistration.Dynamic openInView = container.addFilter("OpenInView", new OpenEntityManagerInViewFilter());
        openInView.addMappingForUrlPatterns(null, true, "/*");

    }
}