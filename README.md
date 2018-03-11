# membership
Membership service (to demonstrate graphQL)

TODO: Get swagger & swagger ui to work


SQL: (TODO: flyway ??)

CREATE SCHEMA `membership` DEFAULT CHARACTER SET utf8 ;

http://localhost:8080/membership-service/graphql?query={allLinks{url}}

http://facebook.github.io/graphql

register servlet:

@WebListener
public class GraphQLServletRegistrationServletContextListener implements ServletContextListener {

    private GraphQLSchema schema;

    public GraphQLServletRegistrationServletContextListener() {}

    @Inject
    public GraphQLServletRegistrationServletContextListener(final GraphQLSchema schema) {
        this.schema = schema;
    }

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        final ServletRegistration.Dynamic dynamicGraphQLServlet
                = event.getServletContext().addServlet("GraphQLEndpoint", SimpleGraphQLServlet.create(schema));
        dynamicGraphQLServlet.addMapping("/graphql");
    }

    @Override
    public void contextDestroyed(final ServletContextEvent event) {}

}