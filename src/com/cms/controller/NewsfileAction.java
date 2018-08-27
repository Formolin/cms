package com.cms.controller;

import com.cms.domain.News;
import com.cms.domain.Newsfile;
import com.cms.service.INewsfileService;
import com.cms.service.NewsfileServiceImpl;
import com.cms.util.ManagerThreadLocal;
import com.cms.util.ProxyFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NewsfileAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
//        INewsfileService newsfileService = new NewsfileServiceImpl();
        //如果没有附件，直接跳转到newsAction?flag=release
        //判断是from是否提交附件  enctype
        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
        if (multipartContent) {
            // 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全

           upload(request,response);
           //文件上传成功后 跳到下载页面

        } else {
            //如果没有附件，直接跳转到newsAction?flag=release
            request.getRequestDispatcher("newsAction?flag=release").forward(request, response);
        }

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("refresh", "3;url='/cms/downloadAction?flag=upload'");
        response.getWriter().write(
                "恭喜您上传成功，将在3秒后跳转到下载页面，如果没有跳转，请点击<a href='/cms/downloadAction?flag=upload'>download</a>");

    }


    private String makeFileName(String filename) {
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;

    }


    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
//        INewsfileService newsfileService = new NewsfileServiceImpl();
        INewsfileService newsfileService = ProxyFactory.getProxyNewfileService();
        File file = new File("/Users/liujiang/upload");
        if (!file.exists()) {
            // 创建临时目录
            file.mkdir();
        }
        try {
            //创建工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //创建文件上传对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            //获得web页提交的数据，返回的集合
            // 监听文件上传进度
            servletFileUpload.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                    /**
                     * 文件大小为：14608,当前已处理：4096 文件大小为：14608,当前已处理：7367
                     * 文件大小为：14608,当前已处理：11419 文件大小为：14608,当前已处理：14608
                     */
                }
            });
// 解决上传文件名的中文乱码
            servletFileUpload.setHeaderEncoding("UTF-8");
            // 设置上传单个文件的大小的最大值，目前是设置为1024*1024*10，也就是10MB
            servletFileUpload.setFileSizeMax(1024 * 1024*10);
            // 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为20MB
            servletFileUpload.setSizeMax(1024 * 1024 * 20);
            List<FileItem> items = servletFileUpload.parseRequest(request);
            String title = "";
            int typeid = 0;
            String content = "";
            String saveFilename = "";
            News news = new News();
            Newsfile newsfile = new Newsfile();
            StringBuilder stringBuilder = new StringBuilder();
            for (FileItem item : items) {
                //如果有附件上传，调用service
//                    System.out.println("item.isFormField()="+item.isFormField());//是否是正常表单域
//                    System.out.println(" item.getName()="+ item.getName());  //获得文件全名称，包含扩展名
//                    System.out.println("item.getFieldName()="+item.getFieldName());//获取表单的name
//                    System.out.println("item.getSize()="+item.getSize());
//                    System.out.println("item.isInMemory()="+item.isInMemory());

                if (item.isFormField()) {
                    String name = item.getFieldName();
                    if ("title".equals(name)) {
                        title = item.getString("utf-8");

                    }
                    if ("typeid".equals(name)) {
                        typeid = Integer.parseInt(item.getString());
                    }
                    if ("content".equals(name)) {
                        content = item.getString("utf-8");
                    }
                } else {
// 如果fileitem中封装的是上传文件
                    // 得到上传的文件名称，
                    String filename = item.getName();
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    // 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
                    // c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("/") + 1);
                    // 得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
                    // 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是：" + fileExtName);
                    // 获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    // 得到文件保存的名称
                    saveFilename = makeFileName(filename);
                    System.out.println("saveFilename="+saveFilename);
                    stringBuilder = stringBuilder.append(saveFilename+"&");
                    // 创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(file + "/" + saveFilename);
                    // 创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    // 判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    // 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        // 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
                        // + filename)当中
                        out.write(buffer, 0, len);
                    }
                    // 关闭输入流
                    in.close();
                    // 关闭输出流
                    out.close();
                    // 删除处理文件上传时生成的临时文件
                    item.delete();
                }


            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String creattime = sdf.format(new Date());
            System.out.println("title=" + title);
            System.out.println("typeid=" + typeid);
            System.out.println("content=" + content);
            System.out.println("creattime=" + creattime);
            news.setTitle(title);
            news.setTypeid(typeid);
            news.setContent(content);
            news.setCreattime(creattime);
            int addNewsLastId = newsfileService.getAddNewsLastId(news);
            System.out.println("last-id=="+addNewsLastId);
            newsfile.setNewsid(addNewsLastId);
//            String[] split = stringBuilder.toString().split("&");
//            for (int i = 0; i < split.length; i++) {
////                System.out.println(split[i]+"---");
//                String filePath = file + "/" + split[i];
//            }
            String filePath = file + "/" + stringBuilder.toString();
            System.out.println("filepath==="+filePath);
            newsfile.setFilename(filePath);
//            int i = 10/0;
            newsfileService.addNewsfile(newsfile);


        } catch (FileUploadException e) {
            e.printStackTrace();
        }finally {
            ManagerThreadLocal.close();
        }
    }
}
