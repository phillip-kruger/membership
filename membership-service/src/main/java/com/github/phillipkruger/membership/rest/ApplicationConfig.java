package com.github.phillipkruger.membership.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Activate JAX-RS. 
 * All REST Endpoints available under /api
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@ApplicationPath("/rest")
//@SwaggerDefinition(basePath = "/rest",info = @Info(title = "Membership API",version = "1.0.0"))
public class ApplicationConfig extends Application {

}
