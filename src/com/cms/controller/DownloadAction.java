package com.cms.controller;


import com.cms.service.INewsfileService;
import com.cms.service.NewsfileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class DownloadAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        INewsfileService newsfileService = new NewsfileServiceImpl();
       if ("upload".equals(request.getParameter("flag"))){
           //获取上传文件的目录
//        String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
           File filePath = new File("/Users/liujiang/upload");
           //存储要下载的文件名
           Map<String,String> fileNameMap = new HashMap<String,String>();
           //递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
           listfile(filePath,fileNameMap);//File既可以代表一个文件也可以代表一个目录
           //将Map集合发送到listfile.jsp页面进行显示
           request.setAttribute("fileNameMap", fileNameMap);
           request.getRequestDispatcher("/normalPage/download.jsp").forward(request, response);
       }

       if ("down".equals(request.getParameter("flag"))){
           //下载---数据库
//           System.out.println("sqldown");
           if (request.getParameter("newsid").trim() == "" || "".equals(request.getParameter("newsid"))){

               request.getRequestDispatcher("/normalPage/download.jsp").forward(request, response);

           }
           int newsid = Integer.parseInt(request.getParameter("newsid"));
           String fileName = newsfileService.getFileName(newsid);
           if ("".equals(fileName) || fileName.trim() ==""){
               //显示没有附件下载
               System.out.println("没有附件");
               request.setAttribute("msg","该新闻没有附件可下载！");
               request.getRequestDispatcher("/normalPage/download.jsp").forward(request,response);
               return;
           }
           System.out.println("filename==="+fileName);
           String substring = fileName.substring(fileName.lastIndexOf("/") + 1);
           String[] realnames = substring.split("&");
           for (int i = 0; i <realnames.length ; i++) {

               System.out.println("realnames===="+realnames[i]);
           }
           request.setAttribute("realnames",realnames);
           request.getRequestDispatcher("/normalPage/download.jsp").forward(request, response);
       }


    }



    /**
     * @Method: listfile
     * @Description: 递归遍历指定目录下的所有文件
     * @param file 即代表一个文件，也代表一个文件目录
     * @param map 存储文件名的Map集合
     */
    public void listfile(File file,Map<String,String> map){
        //如果file代表的不是一个文件，而是一个目录
        if(!file.isFile()){
            //列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            //遍历files[]数组
            for(File f : files){
                //递归
                listfile(f,map);
            }
        }else{
            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
             那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_")+1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            map.put(file.getName(), realName);
        }
    }




}
