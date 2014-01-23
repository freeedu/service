package org.personal.mason.feop.oauth.contact.config;

public class WebAppInitializer{}
//public class WebAppInitializer implements WebApplicationInitializer {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(RootConfig.class, CommonConfig.class);
//        ctx.refresh();
//
//        servletContext.addListener(new ContextLoaderListener(ctx));
//        servletContext.setInitParameter("defaultHtmlEscape", "true");
//
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WebMvcConfig.class);
//
//        FilterRegistration.Dynamic registration = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
//        registration.addMappingForUrlPatterns(null, true, "/*");
//
//        registration = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
//        registration.setInitParameter("encoding", "UTF-8");
//        registration.setInitParameter("forceEncoding", "true");
//        registration.addMappingForUrlPatterns(null, true, "/*");
//
//        ServletRegistration.Dynamic appServlet = servletContext.addServlet("contact", new DispatcherServlet(context));
//        appServlet.setLoadOnStartup(1);
//        Set<String> mappingConflicts = appServlet.addMapping("/");
//        if (!mappingConflicts.isEmpty()) {
//            for (String s : mappingConflicts) {
//                logger.error(String.format("Mapping conflict: %s", s));
//            }
//            throw new IllegalStateException("'appServlet' cannot be mapped to '/'");
//        }
//    }
//
//}
