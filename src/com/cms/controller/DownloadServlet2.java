package com.cms.controller;

import com.cms.service.INewsfileService;
import com.cms.service.NewsfileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class DownloadServlet2 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        INewsfileService newsfileService = new NewsfileServiceImpl();
        PrintWriter writer = response.getWriter();
        writer.print("查询");

        if ("".equals(request.getParameter("newsid")) || request.getParameter("newsid").trim() == ""){
//               System.out.println("123");
            request.getRequestDispatcher("/downloadAction?flag=upload").forward(request,response);
        }
        int newsid = Integer.parseInt(request.getParameter("newsid"));
        System.out.println(newsid);
        String fileName = newsfileService.getFileName(newsid);
        System.out.println("获取的文件名："+fileName);
        String newFilename = fileName.substring(fileName.lastIndexOf("/") + 1);
        System.out.println("newfielname=="+newFilename);
        //处理文件名
        String[] realname = newFilename.split("&");
        File filePath = new File("/Users/liujiang/upload");

        FileInputStream in = null;
        OutputStream out = null;
        for (int i = 0; i <realname.length ; i++) {
            System.out.println("filenames=="+realname[i]);
//               String name = realname[i].substring(realname[i].lastIndexOf("_") + 1);
            String name = realname[i];
            File file = new File(filePath+"/"+name);
            System.out.println("获取文件下载路径："+file);
            //如果文件不存在
            if(!file.exists()){
//            request.setAttribute("message", "您要下载的资源已被删除！！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
                System.out.println("您要下载的资源已被删除！！");
                return;
            }
             System.out.println("io");
//读取要下载的文件，保存到文件输入流
            in = new FileInputStream(filePath+"/"+name);
            //创建输出流
            out = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区当中
            while((len=in.read(buffer))>0){
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
//设置响应头，控制浏览器下载该文件,如果不设置响应头，则为直接打开状态，输出的是字符流，会乱码
               response.setHeader("content-disposition", "attachment;realname[i]=" + URLEncoder.encode(realname[i], "UTF-8"));



        }
////关闭文件输入流
//        in.close();
//        //关闭输出流
//        out.close();


        //读取要下载的文件，保存到文件输入流
        //创建输出流
        //创建缓冲区
//           /循环将输入流中的内容读取到缓冲区当中
        //输出缓冲区的内容到浏览器，实现文件下载
        //关闭文件输入流
        //关闭输出流




    }





	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
