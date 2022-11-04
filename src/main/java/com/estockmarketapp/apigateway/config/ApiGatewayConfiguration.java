package com.estockmarketapp.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estockmarketapp.apigateway.filter.JwtAuthenticationFilter;



@Configuration
public class ApiGatewayConfiguration {
	
	@Autowired
	JwtAuthenticationFilter filter;
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
		.route("security-service", p-> p.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://security-service"))
		.route("company-info-service", p-> p.path("/company/**").filters(f -> f.filter(filter)).uri("lb://company-info-service"))
		.route("stock-info-services", p-> p.path("/stock/**").filters(f -> f.filter(filter)).uri("lb://stock-info-services"))
		.route("estock-market-info-reader", p-> p.path("/market/**").filters(f -> f.filter(filter)).uri("lb://estock-market-info-reader")) 
		.build();
	}
	
}
