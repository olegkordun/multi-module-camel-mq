package me.kordun.office.backend.camel;

import me.kordun.messaging.routing.Routes;
import org.apache.camel.builder.RouteBuilder;

public class BackendRouteBuilder extends RouteBuilder {
    /**
     * Building routes from query to service bean
     */
    @Override
    public void configure() throws Exception {
        from(Routes.COMPANY_SERVICE).
                to("bean:" + BeanOfficeBackendNames.COMPANY_SERVICE);
        from(Routes.USER_SERVICE).
                to("bean:" + BeanOfficeBackendNames.USER_SERVICE);
    }
}