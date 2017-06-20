package com.system.images.auth.shiro;

import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.entity.User;
import com.system.images.auth.kit.ShiroKit;
import com.system.images.auth.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 作者： Administrator
 * 创建时间：2017-04-22.
 * 版本：1.0
 */
public class MyRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    /**
     * 获取授权信息
     * @param principalCollection 身份集合
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) principalCollection.getPrimaryPrincipal(); //直接可以获取到之前传入的用户
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("账号:"+user.getAccount());
        if(user.getAccount().equals(User.ADMIN_USER_NAME)){
            logger.info("授权所有:"+user.getAccount());
            info.addStringPermission("/**");
            logger.info("[授权结束:ADMIN]");
            return info;
        }
        //普通用户
        logger.info((user.toString()));
        System.out.println("CommonUser");
        List<AuthResource> resourceList = userService.listResourseByUserId(user.getId());

        Set<String> permissions = new HashSet<String>();
        if(resourceList != null && resourceList.size()>0){
            for (AuthResource res : resourceList){
                if(res.getUrl() != null && !res.getUrl().trim().equals("") && !res.getDisplay().equals(AuthResource.UNENABLED)){
                    permissions.add(res.getUrl());  //授权该用户所有可以访问的资源
                }
            }
            info.setStringPermissions(permissions);
            logger.info("[授权结束]");
            return info;
        }
        logger.info("[授权结束]");
        return null;
    }

    /**
     * 获取身份验证相关信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("[开始认证]");
        String account = authenticationToken.getPrincipal().toString();
        String password = new String((char[])authenticationToken.getCredentials());
        User user = null;
        user = userService.login(account, password);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(account+password));
        logger.info("[结束认证]");
        return info;
    }
}
