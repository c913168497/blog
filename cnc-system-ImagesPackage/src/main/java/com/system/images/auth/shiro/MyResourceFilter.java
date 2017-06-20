package com.system.images.auth.shiro;

import com.cnc.common.lang.utils.JSONUtil;
import com.cnc.common.web.util.MessageBox;
import com.system.images.auth.entity.User;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.util.PatternMatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**访问时，url权限控制
 * 作者： Administrator
 * 创建时间：2017-04-24.
 * 版本：1.0
 */
public class MyResourceFilter extends AccessControlFilter{

    private String errorUrl;                //权限不足时跳转页面
    private Set<String> accessAllowedUrls;  //通过验证时可以访问的资源URL


    public String getErrorUrl() {return errorUrl;}
    public void setErrorUrl(String errorUrl) {this.errorUrl = errorUrl;}
    public Set<String> getAccessAllowedUrls() {return accessAllowedUrls;}
    public void setAccessAllowedUrls(Set<String> accessAllowedUrls) {this.accessAllowedUrls = accessAllowedUrls;}


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String url = getPathWithinApplication(servletRequest);

        System.out.println("allow:"+url+"");
        if (url != null && !url.trim().equals("")){
            //只需要通过验证就可以访问的资源URL
            PatternMatcher patternMatcher = new AntPathMatcher();
            for (String accessAllowedUrl : accessAllowedUrls) {
                if (patternMatcher.matches(accessAllowedUrl,url)){
                    System.out.printf("[url:{%s}]允许访问", url);
                    return true;
                }
            }
            return subject.isPermitted(url);
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse hsp = (HttpServletResponse) servletResponse;
        HttpServletRequest hsq = (HttpServletRequest) servletRequest;
        hsp.setCharacterEncoding("UTF-8");
        hsp.setHeader("Content-type", "application/json;charset=UTF-8");
        if (isAjaxRequest(hsq)){
            System.out.println("没有访问权限");
            hsp.setStatus(302);
            hsp.getWriter().write(JSONUtil.object2Json(MessageBox.getErrorMsg("没有访问权限")));
        }else {
            Subject subject = getSubject(servletRequest, servletResponse);
            User user = (User) subject.getPrincipal();
            hsp.sendRedirect(hsq.getContextPath()+ "/" + this.errorUrl);
        }
        return false;
    }

    protected boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(header) ? true:false;  //忽略大小写比较是否是Ajax请求。
        return isAjax;
    }
}
