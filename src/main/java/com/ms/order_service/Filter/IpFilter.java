package com.ms.order_service.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@Profile("prod")
public class IpFilter extends OncePerRequestFilter {

    @Value("${server.allow.id}")
    private String ALLOW_IP;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String[] ips = ALLOW_IP.split(",");
        String clientIp = request.getRemoteAddr();
        if (Arrays.stream(ips).noneMatch(item->item.equals(clientIp))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            return;
        }
        filterChain.doFilter(request,response);
    }
}
