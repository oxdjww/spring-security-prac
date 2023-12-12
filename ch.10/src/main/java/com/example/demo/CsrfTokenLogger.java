//package com.example.demo;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import java.io.IOException;
//import java.util.logging.Logger;
//import org.springframework.security.web.csrf.CsrfToken;
//
//public class CsrfTokenLogger implements Filter {
//    private Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        Object o = request.getAttribute("_csrf");
//        CsrfToken token = (CsrfToken) o;
//
//        logger.info("CSRF token " + token.getToken());
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
