package org.dsp.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类配置网关路由映射规则
 */
@Configuration
public class GatewayConfig {
    /**
     * 配置一个Id为 route-name的路由规则，当访问地址http://localhost:9527/guonei时会自动转发到地址http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_xxx1",
                r->r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();

        return routes.build();
    }

}
