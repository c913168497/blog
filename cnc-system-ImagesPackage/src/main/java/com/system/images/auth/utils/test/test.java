package com.system.images.auth.utils.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 作者： Administrator
 * 创建时间：2017-05-12.
 * 版本：1.0
 */
public class test {

    public static void main(String[] args) throws Exception {
        toIrc();
    }

    private static String toIrc() throws Exception {
        List<String> listSet = readSensitiveWordFile();

        String irc = "str+=";
        Iterator<String> iterator = listSet.iterator();
        while (iterator.hasNext()){
            String strList=(String)iterator.next();
            irc = irc +"\""+ strList+"\\n\"+"+"\n";
        }
        System.out.println(irc);
        return irc;
    }

    private static List<String> readSensitiveWordFile() throws Exception{
        List<String> set = new ArrayList<String>();
        String dir ="g:/tmp/jusAdream.txt";
        File file = new File(dir);    //读取文件
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
        try {
            if(file.isFile() && file.exists()){      //文件流是否存在
                BufferedReader bufferedReader = new BufferedReader(read);
                String txt = null;
                while((txt = bufferedReader.readLine()) != null){    //读取文件，将文件内容放入到set中
                    set.add(txt+"");
                }
            }
            else{         //不存在抛出异常信息
                throw new Exception("敏感词库文件不存在");
            }
        } catch (Exception e) {
            throw e;
        }finally{
            read.close();     //关闭文件流
        }
        return set;
    }
}
