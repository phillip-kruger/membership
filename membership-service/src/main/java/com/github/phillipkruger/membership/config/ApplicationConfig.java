package com.github.phillipkruger.membership.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Activate JAX-RS. 
 * All REST Endpoints available under /api
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@ApplicationPath("/api")
//@SwaggerDefinition(basePath = "/api",info = @Info(title = "Membership API",version = "1.0.0"))
public class ApplicationConfig extends Application {

}
