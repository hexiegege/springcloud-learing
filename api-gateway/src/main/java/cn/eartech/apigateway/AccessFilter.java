package cn.eartech.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @des: 自定义请求过滤器
 * @author: shanfa
 * @date: 2019/1/24 18:08
 */
public class AccessFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        if(accessToken == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(403);
            return null;
        }
        return null;
    }
}
