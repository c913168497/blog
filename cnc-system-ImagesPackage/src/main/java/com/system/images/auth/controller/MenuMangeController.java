package com.system.images.auth.controller;

import com.cnc.common.lang.page.PageBean;
import com.cnc.common.lang.utils.JSONUtil;
import com.cnc.common.web.ezPage.EzPage;
import com.cnc.common.web.ezPage.EzPageParam;
import com.cnc.common.web.ezPage.PageUtils;
import com.cnc.common.web.util.MessageBox;
import com.cnc.common.web.util.SpringValidUtil;
import com.system.images.auth.dto.AuthResDto;
import com.system.images.auth.dto.TreeDto;
import com.system.images.auth.entity.AuthResource;
import com.system.images.auth.service.AuthResourceService;
import com.system.images.auth.utils.DtoUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 作者： Administrator
 * 创建时间：2017-05-11.
 * 版本：1.0
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuMangeController {
  private final Logger logger = LoggerFactory.getLogger(MenuMangeController.class);

    @Autowired
    private AuthResourceService authResourceService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
            return "auth/menu_manage";
    }

    /**
     * 菜单列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public EzPage<AuthResDto> getMenulist(){
        logger.info("进入");
        List<AuthResDto> treeDtoList = DtoUtils.res2resDto(authResourceService.authResourceList(AuthResource.MENU_RESOURCE));
        EzPage<AuthResDto> rs = new EzPage<AuthResDto>();
        rs.setRows(treeDtoList);
        rs.setTotal(treeDtoList == null ? 0 : treeDtoList.size());
        System.out.println(JSONUtil.object2Json(rs));
        return  rs;
    }

    /**
     * 菜单结构树
     * @return
     */
    @RequestMapping(value = "/list/tree", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TreeDto> getTreeList(){
        return DtoUtils.res2treeDto(authResourceService.authResourceList(AuthResource.MENU_RESOURCE));
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MessageBox addMenu(@Valid AuthResource authResource, BindingResult result){
        System.out.println("进入添加" + authResource.toString());
        if(result.hasErrors()){
            String erorStr = SpringValidUtil.getErrors2Str(result);
            return  MessageBox.getErrorMsg("菜单添加失败:"+erorStr);
        }else {
            AuthResource pMenu  = authResourceService.getById(authResource.getPid());
            //判断该菜单是否存在
            List<AuthResource> childMenus = authResourceService.listAuthResourceByPId(authResource.getPid());
            if(childMenus!= null && childMenus.size()>0){
                for (AuthResource childMenu : childMenus) {
                    if(childMenu.getName().equals(authResource.getName())){
                        return MessageBox.getErrorMsg("添加菜单失败，该菜单已经存在！");
                    }
                }
            }
            authResource.setMenupos("0");//设置默认值为0;
            authResource.setIcon("");//设置图标样式为"";
            authResource.setOrdernum("0");
            if(authResource.getType()==2){
                authResource.setType(AuthResource.FUN_RESOURCE);
            }
            if(authResource.getType()==1){
                authResource.setType(AuthResource.MENU_RESOURCE);
            }
            authResource.setPsn(pMenu.getPsn());
            authResourceService.create(authResource);
            System.out.println("添加成功!");
            return MessageBox.getSuccessMsg("添加菜单成功!");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MessageBox updataMenu(AuthResource authResource){
        logger.info("进入修改" + authResource.toString());
        AuthResource pMenu  = authResourceService.getById(authResource.getPid());
            //判断该菜单是否存在
            List<AuthResource> childMenus = authResourceService.listAuthResourceByPId(authResource.getPid());
            if(childMenus!= null && childMenus.size()>0){
                for (AuthResource childMenu : childMenus) {
                    if(childMenu.getName().equals(authResource.getName()) && !childMenu.getId().equals(authResource.getId())){
                        return MessageBox.getErrorMsg("修改菜单失败，该菜单已经存在！");
                    }
                }
            }
        authResource.setMenupos("0");//设置默认值为0;
        authResource.setIcon("");//设置图标样式为"";
        authResource.setOrdernum("0");
        authResource.setPsn(pMenu.getPsn());
        authResourceService.update(authResource);
        System.out.println("修改成功!");
        return MessageBox.getSuccessMsg("修改成功");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MessageBox deleteMennu(@PathVariable Long id){
        if(authResourceService.listAuthResourceByPId(id).size()>0){
            return MessageBox.getErrorMsg("菜单下有子菜单无法进行删除");
        }
        authResourceService.deleteById(id);
        return MessageBox.getSuccessMsg("删除成功!");
    }
    /**
     * 功能列表菜单
     * @param ezPageParam 分页参数
     * @param id 功能所属菜单id
     * @return
     */
    @RequestMapping(value = "/fun/list/{id}", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public EzPage<AuthResource> getFunMenu(EzPageParam ezPageParam,@PathVariable Long id){
        PageBean<AuthResource> authResourceList = authResourceService.findResourceByPidAndType(PageUtils.convert(ezPageParam),id,AuthResource.FUN_RESOURCE);
        return PageUtils.convertPageBean(authResourceList);
    }



}
