package com.bookstore.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

	public static final List<String> openApis = List.of("/login/register", "/login/auth","/eureka");

	Predicate<ServerHttpRequest> isSecured = request -> openApis.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
}
