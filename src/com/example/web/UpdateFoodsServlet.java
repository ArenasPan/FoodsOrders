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
import java.util.List;
import java.util.UUID;

/**
 * Created by Pan on 16/12/4.
 */
@WebServlet(name = "UpdateFoodsServlet", urlPatterns = "/servlet/UpdateFoodsServlet")
public class UpdateFoodsServlet extends HttpServlet {
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
                @Override
                public void update(long pBytesRead, long pContentLength, int pItems) {
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
                            case "id":
                                foods.setId(Integer.parseInt(value));
                                break;
                            case "name":
                                foods.setName(value);
                                break;
                            case "category":
                                foods.setCategory(value);
                                break;
                            case "price":
                                foods.setPrice(Integer.parseInt(value));
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
                        new File(path).mkdirs();
                        File file = new File(path, uuidName);
                        foods.setImageUrl("images/" + uuidName);
                        FileOutputStream out = new FileOutputStream(file);
                        byte[] data = new byte[1024];
                        int i;
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
            //2.调用service中的方法添加数据
            //首先删除原有的图片
            service.deleteImageByName(foods.getName());
            //然后更新数据
            service.updateCust(foods);
            //3.返回首页
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
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
