package com.wt.arcgis.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@Configuration
public class FilterClass{
    @Bean
    public FilterRegistrationBean testFilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
}

class MyFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();


        String url = request.getRequestURI();
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

        if(url.endsWith("login") || url.endsWith("getRole") ||url.endsWith("getPost") ||url.endsWith("getUserInfo") ){
            filterChain.doFilter(servletRequest,servletResponse);//放行
            return;
        }


        if(null == session.getAttribute("user")){
            String json = "{" + '"' + "result" + '"' + ":" + '"' + "error" + '"' + "}";

            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();

            return;
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);//放行
        }
    }

    @Override
    public void destroy(){
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
    }
}