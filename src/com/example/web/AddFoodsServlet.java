package com.example.web;

import com.example.domain.Foods;
import com.example.factory.BasicFactory;
import com.example.service.FoodsService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Pan on 16/12/3.
 */
@WebServlet(name = "AddFoodsServlet", urlPatterns = "/servlet/AddFoodsServlet")
public class AddFoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        FoodsService service = BasicFactory.getFactory().getInstance(FoodsService.class);

        try {
            //1.创建工厂
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            fileItemFactory.setSizeThreshold(100 * 1024); //设置内存最大缓存为100kb
//            new File(getServletContext().getRealPath("WEB-INF/temp")).mkdirs();
//            fileItemFactory.setRepository(new File(getServletContext().getRealPath("WEB-INF/temp"))); //设置文件缓存到WEB-INF/temp目录下
            //2.生产文件上传核心类
            ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
            if (!ServletFileUpload.isMultipartContent(request)) {
                throw new RuntimeException("必须是文件上传");
            }
            //upload.setFileSizeMax(1024 * 500); //设置单个文件上传最大值为500KB
            //upload.setSizeMax(1024 * 1024 * 10); //设置所有文件总上传最大值10M
            upload.setProgressListener(new ProgressListener() {
                private long startTime = System.currentTimeMillis();
                @Override
                public void update(long pBytesRead, long pContentLength, int pItems) {
                    //获取已上传数据大小，单位KB
                    BigDecimal br = new BigDecimal(pBytesRead).divide(new BigDecimal(1024), 2, BigDecimal.ROUND_HALF_UP);
                    //获取文件总大小，单位KB
                    BigDecimal cl = new BigDecimal(pContentLength).divide(new BigDecimal(1024), 2, BigDecimal.ROUND_HALF_UP);
                    //获取上传剩余大小
                    BigDecimal ll = cl.subtract(br);
                    //获取上传百分比
                    BigDecimal per = br.multiply(new BigDecimal(100)).divide(cl, 2, BigDecimal.ROUND_HALF_UP);
                    System.out.println("当前读取的是第" + pItems + "个上传项" + ",文件总大小" + cl + "KB" + ",已上传" + br + "KB" + "剩余" + ll + "KB" + "已完成" + per + "%");
                    //获取上传时间
                    long currentTime = System.currentTimeMillis();
                    long useTime = (currentTime - startTime) / 1000;
                    System.out.print("用时" + useTime + "秒");
                    //获取上传速度
                    BigDecimal speed = new BigDecimal(0);
                    if (useTime != 0) {
                        speed = br.divide(new BigDecimal(useTime), 2, BigDecimal.ROUND_HALF_UP);
                    }
                    System.out.print("下载速度" + speed + "KB/s");
                    //获取剩余时间
                    BigDecimal leftTime = new BigDecimal(0);
                    if (!speed.equals(new BigDecimal(0))) {
                        leftTime = ll.divide(speed, 2, BigDecimal.ROUND_HALF_UP);
                    }
                    System.out.print("剩余时间" + leftTime + "秒");
                }
            });

            //1.获取数据，校验数据
            Foods foods = new Foods();

            List<FileItem> list = upload.parseRequest(request);
            if (list != null && list.size() != 0) {
                for (FileItem item : list) {
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString("utf-8");
                        switch (name) {
                            case "name":
                                foods.setName(value);
                                break;
                            case "price":
                                foods.setPrice(Integer.parseInt(value));
                                break;
                            case "category":
                                foods.setCategory(value);
                                break;
                            case "description":
                                foods.setDescription(value);
                                break;
                        }
                        System.out.println(name + ":" + value + "\n");
                    } else {
                        String uuidName = UUID.randomUUID().toString() + "_" + item.getName();
                        InputStream in = item.getInputStream();
                        String path = getServletContext().getRealPath("images");

//                        int hashCode = uuidName.hashCode();
//                        String hexStr = Integer.toHexString(hashCode);
//                        char[] hsArray = hexStr.toCharArray();
//                        for (char dir : hsArray) {
//                            path += "/" + dir;
//                        }
                        new File(path).mkdirs();
                        File file = new File(path, uuidName);
                        foods.setImageUrl("images/" + uuidName);
                        FileOutputStream out = new FileOutputStream(file);
                        byte[] data = new byte[1024];
                        int i = 0;
                        while ((i = in.read(data)) != -1) {
                            out.write(data, 0, i);
                        }
                        out.close();
                        in.close();
                        item.delete();
                    }
                }
            }
            foods.validate();
//            String[] prefs = request.getParameterValues("preference");
//            StringBuffer buffer = new StringBuffer();
//            for (String pref : prefs) {
//                buffer.append(pref).append(",");
//            }
//            String preference = buffer.substring(0, buffer.length() - 1);
//            foods.setPreference(preference);
            //2.调用service中的方法添加数据
            service.addCust(foods);
            //3.返回首页
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            response.getWriter().write("单个文件不超过10M,总大小不超过100M!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {

//            BeanUtils.populate(cust, request.getParameterMap());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
