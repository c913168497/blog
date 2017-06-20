package com.cnc.util.mybatis.tool.utils;


import com.cnc.util.mybatis.tool.model.ColumnModel;
import com.cnc.util.mybatis.tool.model.TableModel;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016-12-16.
 */
public class CodeAutoUtil extends AutoCoreUtil {


    //tostring的模板
    public static final String TOSTR_TEMP="\"[CLASS_NAME]{\"+[TOSTR]+\"}\"";
    //字段的模板
    public static final String FILE_TEMP ="\t@Label(\"[REMARKS]\")" +NEWLINES+"\tprivate [FILED_TYPE] [FILED_NAME];"+NEWLINES;
    //方法的模板
    public static final String METHOD_TEMP ="\t"+"public [FILED_TYPE] get[METHOD_NAME](){return this.[FILED_NAME];}" +NEWLINES+"\t"
                                                +"public void set[METHOD_NAME]([FILED_TYPE] [FILED_NAME]){this.[FILED_NAME] = [FILED_NAME];}"+NEWLINES+NEWLINES;
    //各个模板的路径
    public static final String BEAN_TEMPLATE = BASE_TEMP+"bean.template";
    public static final String DAO_TEMPLATE = BASE_TEMP+"beanDao.template";
    public static final String DAO_IMPL_TEMPLATE = BASE_TEMP+"beanDaoImpl.template";
    public static final String SERVICE_TEMPLATE = BASE_TEMP+"beanService.template";
    public static final String SERVICE_IMPL_TEMPLATE = BASE_TEMP+"beanServiceImpl.template";

    //后缀
    public static final String BEAN_SUFFIX=".java";
    public static final String DAO_SUFFIX="Dao.java";
    public static final String DAO_IMPL_SUFFIX="DaoImpl.java";
    public static final String SERVICE_SUFFIX="Service.java";
    public static final String SERVICE_IMPL_SUFFIX="ServiceImpl.java";
    @Override
    protected void create(DBType type, TableModel tableModel, String packageName, String saveDirectory) {
        File beanSavePath = new File(saveDirectory+"/entity");
        File daoSavePath = new File(saveDirectory+"/dao");
        File daoImplSavePath = new File(saveDirectory+"/dao/impl");
        File serviceSavePath = new File(saveDirectory+"/service");
        File serviceImplSavePath = new File(saveDirectory+"/service/impl");
        mkdirs(beanSavePath, daoSavePath, daoImplSavePath, serviceSavePath, serviceImplSavePath);

        //bean
        String BEAN_IMPORT = "";//引包
        String NOW_DATE = new Date().toLocaleString();//创建日期
        String BEAN_NAME = "";//类名
        String BEAN_NAME_LESS ="";//类名首字母小写
        String BEAN_FILE = "";//类的字段
        String BEAN_METHOD = "";//类的方法
        String BEAN_TOSTR_STR = "";//tostring返回类型
        String BEAN_PACKAGE = "";//实体类包名
        //dao
        String DAO_PACKAGE="";
        String DAO_IMPORT = "";
        //daoimpl
        String DAO_IMPL_PACKAGE = "";
        String DAO_IMPL_IMPORT = "";

        //service
        String SERVICE_PACKAGE="";
        String SERVICE_IMPORT="";

        //serviceImpl
        String SERVICE_IMPL_PACKAGE="";
        String SERVICE_IMPL_IMPORT="";

        String tableName = tableModel.getTableName();
        //t_xx => xx
        if(tableName.toLowerCase().startsWith("t_")){
            tableName = tableName.substring(2);
        }
        BEAN_NAME =  Character.toUpperCase(tableName.charAt(0))+toCamelString(tableName.substring(1));
        BEAN_NAME_LESS =  Character.toLowerCase(BEAN_NAME.charAt(0))+BEAN_NAME.substring(1);
        if(packageName != null && !packageName.trim().equals("")){
            BEAN_PACKAGE = "package "+packageName+".entity;";

            DAO_PACKAGE  = "package "+packageName+".dao;";
            DAO_IMPORT = "import "+packageName+".entity."+BEAN_NAME+";";

            DAO_IMPL_PACKAGE = "package "+packageName+".dao.impl;";
            DAO_IMPL_IMPORT = "import "+packageName+".entity."+BEAN_NAME+";"+NEWLINES
                    +"import "+packageName+".dao."+BEAN_NAME+"Dao;"+NEWLINES;

            SERVICE_PACKAGE = "package "+packageName+".service;";
            SERVICE_IMPORT =  "import "+packageName+".entity."+BEAN_NAME+";";

            SERVICE_IMPL_PACKAGE ="package "+packageName+".service.impl;";
            SERVICE_IMPL_IMPORT = "import "+packageName+".entity."+BEAN_NAME+";"+NEWLINES
                    +"import "+packageName+".dao."+BEAN_NAME+"Dao;"+NEWLINES
                    +"import "+packageName+".service."+BEAN_NAME+"Service;";
        }else{
            DAO_PACKAGE  = "package dao;";
            DAO_IMPORT = "import entity."+BEAN_NAME+";";

            DAO_IMPL_PACKAGE = "package dao.impl;";
            DAO_IMPL_IMPORT = "import entity."+BEAN_NAME+";"+NEWLINES
                    +"import dao."+BEAN_NAME+"Dao;"+NEWLINES;

            SERVICE_PACKAGE = "package service;";
            SERVICE_IMPORT =  "import entity."+BEAN_NAME+";";

            SERVICE_IMPL_PACKAGE ="package service.impl;";
            SERVICE_IMPL_IMPORT = "import entity."+BEAN_NAME+";"+NEWLINES
                    +"import dao."+BEAN_NAME+"Dao;"+NEWLINES
                    +"import service."+BEAN_NAME+"Service;";
        }
        List<ColumnModel> cls  = tableModel.getColumnModels();
        if(cls != null && cls.size()>0){
            StringBuffer fieldsStr = new StringBuffer();
            //不能直接拼接，有重复导包的BUG
            //StringBuffer importStr = new StringBuffer();
            Set<String> improtSet = new HashSet<String>();
            StringBuffer methodStr = new StringBuffer();
            StringBuffer toStr = new StringBuffer();
            for(ColumnModel model: cls){
                if(!model.getColumnName().equalsIgnoreCase("ID")){
                    String filedName = toCamelString(model.getColumnName());
                    String filedType = "String";//如果没有找打对应类型，默认为String
                    String methodName = Character.toUpperCase(filedName.charAt(0))+filedName.substring(1);
                    String javaType = SqlTypeConvertor.convert(JDBCUtils.DB_TYPE,model.getColumnType(),model.getDatasize(),model.getDigits());
                    if(javaType != null){
                        if(!javaType.startsWith("java.lang")){
                            improtSet.add(new StringBuffer().append("import ").append(javaType).append(";").append(NEWLINES).toString());
                        }
                        filedType = javaType.substring(javaType.lastIndexOf(".")+1);
                    }
                    fieldsStr.append(FILE_TEMP.replace("[FILED_TYPE]", filedType).replace("[FILED_NAME]", filedName).replace("[REMARKS]",model.getRemarks() == null?"":model.getRemarks()));
                    methodStr.append(METHOD_TEMP.replace("[FILED_TYPE]", filedType).replace("[METHOD_NAME]", methodName).replace("[FILED_NAME]", filedName));
                    toStr.append("\""+filedName+":\"").append("+" + filedName + "+").append("\",\"").append("+");
                }
            }

            BEAN_FILE = fieldsStr.toString();
            BEAN_METHOD = methodStr.toString();
            StringBuffer importStr = new StringBuffer();
            if(improtSet != null && improtSet.size()>0){
                for(String s : improtSet){
                    importStr.append(s);
                }
            }
            BEAN_IMPORT = importStr.toString();
            BEAN_TOSTR_STR = TOSTR_TEMP.replace("[CLASS_NAME]",BEAN_NAME).replace("[TOSTR]",toStr.substring(0,toStr.length()-5));
        }
        //entity
        String beanTmpStr = file2String(BEAN_TEMPLATE);
        beanTmpStr = beanTmpStr.replace("[BEAN_PACKAGE]", BEAN_PACKAGE).replace("[BEAN_IMPORT]",BEAN_IMPORT)
                .replace("[NOW_DATE]", NOW_DATE).replace("[BEAN_NAME]",BEAN_NAME)
                .replace("[BEAN_FILE]", BEAN_FILE).replace("[BEAN_METHOD]",BEAN_METHOD)
                .replace("[BEAN_TOSTR_STR]", BEAN_TOSTR_STR);
        saveCode(beanTmpStr,new File(beanSavePath,BEAN_NAME+BEAN_SUFFIX));
        System.out.println(BEAN_NAME+BEAN_SUFFIX+"==自动生成完成！");
        //dao
        String daoTmpStr = file2String(DAO_TEMPLATE);
        daoTmpStr = daoTmpStr.replace("[DAO_PACKAGE]",DAO_PACKAGE).replace("[DAO_IMPORT]",DAO_IMPORT)
                .replace("[NOW_DATE]", NOW_DATE).replace("[BEAN_NAME]",BEAN_NAME);
        saveCode(daoTmpStr, new File(daoSavePath, BEAN_NAME + DAO_SUFFIX));
        System.out.println(BEAN_NAME + DAO_SUFFIX + "==自动生成完成！");
        //daoImpl
        String daoImplTmpStr = file2String(DAO_IMPL_TEMPLATE);
        daoImplTmpStr = daoImplTmpStr.replace("[DAO_IMPL_PACKAGE]", DAO_IMPL_PACKAGE).replace("[DAO_IMPL_IMPORT]", DAO_IMPL_IMPORT)
                .replace("[NOW_DATE]", NOW_DATE).replace("[BEAN_NAME]",BEAN_NAME)
                .replace("[BEAN_NAME_LESS]", BEAN_NAME_LESS);
        saveCode(daoImplTmpStr,new File(daoImplSavePath,BEAN_NAME+DAO_IMPL_SUFFIX));
        System.out.println(BEAN_NAME + DAO_IMPL_SUFFIX + "==自动生成完成！");
        //service
        String serviceStr = file2String(SERVICE_TEMPLATE);
        serviceStr = serviceStr.replace("[SERVICE_PACKAGE]", SERVICE_PACKAGE).replace("[SERVICE_IMPORT]", SERVICE_IMPORT)
                .replace("[NOW_DATE]", NOW_DATE).replace("[BEAN_NAME]", BEAN_NAME);
        saveCode(serviceStr,new File(serviceSavePath,BEAN_NAME+SERVICE_SUFFIX));
        System.out.println(BEAN_NAME + SERVICE_SUFFIX + "==自动生成完成！");
        //serviceImpl
        String serviceImplTmpStr = file2String(SERVICE_IMPL_TEMPLATE);
        serviceImplTmpStr = serviceImplTmpStr.replace("[SERVICE_IMPL_PACKAGE]", SERVICE_IMPL_PACKAGE).replace("[SERVICE_IMPL_IMPORT]", SERVICE_IMPL_IMPORT)
                .replace("[NOW_DATE]", NOW_DATE).replace("[BEAN_NAME]",BEAN_NAME)
                .replace("[BEAN_NAME_LESS]", BEAN_NAME_LESS);
        saveCode(serviceImplTmpStr,new File(serviceImplSavePath,BEAN_NAME+SERVICE_IMPL_SUFFIX));
        System.out.println(BEAN_NAME + SERVICE_IMPL_SUFFIX + "==自动生成完成！");
    }
}
