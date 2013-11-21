package org.personal.mason.feop.oauth.contact.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

@Override
public void onStartup(ServletContext servletContext) throws ServletException {
	AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	ctx.register(Application.class);
	
	ServletRegistration.Dynamic dispatcher = servletContext.addServlet("account",
			new DispatcherServlet(ctx));
	dispatcher.setLoadOnStartup(1);
	dispatcher.addMapping("/");
}

}
