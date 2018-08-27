package com.cms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class DownloadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
     	request.setCharacterEncoding("UTF-8");
        //得到要下载的文件名
        String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
        System.out.println("1"+fileName);
       /* 

        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        System.out.println(fileName);*/
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
//        String path=this.getServletContext().getRealPath("/WEB-INF/upload");
        File filePath = new File("/Users/liujiang/upload");
        //得到要下载的文件
        File file = new File(filePath + "/" + fileName);
        System.out.println("2"+file);
        //如果文件不存在
        if(!file.exists()){
//            request.setAttribute("message", "您要下载的资源已被删除！！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        System.out.println("3处理文件名"+realname);
        //设置响应头，控制浏览器下载该文件,如果不设置响应头，则为直接打开状态，输出的是字符流，会乱码
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(filePath + "/" + fileName);
        System.out.println("4"+in);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();


	   

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
