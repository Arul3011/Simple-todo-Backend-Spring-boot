package com.example.my_app.util;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;
import java.io.IOException;
import com.example.my_app.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil; // instance-based usage

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
 String path = request.getRequestURI();
if (
    path.startsWith("/auth/") ||
    path.startsWith("/swagger-ui") ||
    path.startsWith("/v3/api-docs") ||
    path.startsWith("/swagger-resources") ||
    path.startsWith("/webjars")
) {
    filterChain.doFilter(request, response); 
    return;
}

        String authHeader = request.getHeader("Authorization");

        // Check if header exists and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) { 
                // Token is valid → continue request
                filterChain.doFilter(request, response);
                return;
            } else {
                // Invalid token → send 401
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized: Invalid or expired token");
                return;
            }
        }

        // No token → send 401
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("Unauthorized: Missing token");
    }
}
