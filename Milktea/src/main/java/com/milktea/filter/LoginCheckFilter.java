package com.milktea.filter;

import com.alibaba.fastjson.JSON;
import com.milktea.common.BaseContext;
import com.milktea.common.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否完成登入
 */

//设置未过滤器
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取本次请求的URL
        String url = request.getRequestURI();

        //不用过滤的请求
        String[] urls = new String[]{
          "/employee"
        };

        //2,判断本次请求是否需要处理
        boolean check = check(urls,url);

        //3.如果不需要处理，则直接放行
        if (check){
            filterChain.doFilter(request,response);
        }

        //4.判断登入状态，如果已登入，则直接放行
        if (request.getSession().getAttribute("employee") != null){
            Long id = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(id);

            filterChain.doFilter(request,response);
            return;
        }

        //5.如果未登录则返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.error("shibai")));

    }

    /**
     *路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param url
     * @return
     */
    private boolean check(String[] urls, String url) {
        for(String demo: urls){
            Boolean check = PATH_MATCHER.match(demo,url);
            if (check){
                return true;
            }
        }
        return false;
    }
}
