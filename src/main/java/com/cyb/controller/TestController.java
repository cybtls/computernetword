package com.cyb.controller;

import com.cyb.pojo.Category;
import com.cyb.pojo.Student;
import com.cyb.pojo.Test;
import com.cyb.service.StudentService;
import com.cyb.service.TestService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tt")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/yy")
    public List<Test> test()
    {
        List<Test> test = testService.get();
        return test;
    }

    @ResponseBody
    @RequestMapping("/uu")
    public String testadd()
    {
        List<Test> test = testService.add();
        return "1";
    }

    @ResponseBody
    @RequestMapping("/rr")
    public String testupload(@RequestParam("file") MultipartFile multipartFile,@RequestParam("filecategoryid") Integer filecategoryid,
                             @RequestParam("filename") String filename, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("resources/data/document");
        File filePath = new File(path);
        System.out.println("文件保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录：" + filePath);
            filePath.mkdirs();
        }

        //获取原始文件名称
        String originalFileName = multipartFile.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);

        //设置文件新名字
        String fileName = System.currentTimeMillis() + "." + type;
        System.out.println("文件新名称：" + fileName);
        //在指定路径创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            multipartFile.transferTo(targetFile);
            return "ok";
        } catch (IOException e) {
            System.out.println("保存文件错误...");
            e.printStackTrace();
        }
        return "222";
    }

    @ResponseBody
    @RequestMapping("/dd")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {
        try{
            //下载路径
            String path = request.getSession().getServletContext().getRealPath("resources/data/document");
            String fileName = "1552136404559.txt";
            File file = new File(path + File.separator + fileName);
            HttpHeaders headers = new HttpHeaders();
            //解决文件名中文乱码问题
            String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
            //告诉浏览器以"attachment"方式打开文件
            headers.setContentDispositionFormData("attachment",downloadFileName);
            //设置请求头的媒体格式类型为 application/octet-stream(二进制流数据)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("文件下载出错...");
            return null;
        }
    }



}
