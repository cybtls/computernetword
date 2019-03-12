package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Resources;
import com.cyb.service.ResourcesService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;

    private Integer pageSize = 10;

    private Integer code;


    @ResponseBody
    @RequestMapping("/getresources")
    public Map<String, Object> getresources(@RequestBody Map map) {
        Integer categoryid = (Integer) map.get("categoryid");
        String resourcename = (String) map.get("resourcename");
        Integer CurrentpageNum = (Integer) map.get("CurrentpageNum");
        Map<String, Object> back = new HashMap<>();
        Integer count = 0;
        List<Resources> resources;
        if (categoryid == null && resourcename != null) {
            count = resourcesService.getresourcesnum(null, resourcename);
            PageHelper.startPage(CurrentpageNum, pageSize);
            resources = resourcesService.getresources(null, resourcename);
        } else if (categoryid != null && resourcename == null) {
            count = resourcesService.getresourcesnum(categoryid, null);
            PageHelper.startPage(CurrentpageNum, pageSize);
            resources = resourcesService.getresources(categoryid, null);
        } else if (categoryid != null && resourcename != null) {
            count = resourcesService.getresourcesnum(categoryid, resourcename);
            PageHelper.startPage(CurrentpageNum, pageSize);
            resources = resourcesService.getresources(categoryid, resourcename);
        } else {
            count = resourcesService.getresourcesnum(null, null);
            PageHelper.startPage(CurrentpageNum, pageSize);
            resources = resourcesService.getresources(null, null);
        }
        if (count > 0) {
            code = CodeMsg.Code_SUCCESS;
            back.put("count", count);
            back.put("resources", resources);
        } else {
            code = CodeMsg.Code_NOTEXIST;
        }
        back.put("code", code);
        return back;
    }

    @ResponseBody
    @RequestMapping("/delresources")
    public Map<String, Object> delresources(@RequestParam("resid") Integer resid) {
        Resources resources = resourcesService.getresourcebyid(resid);
        File delfile = new File(resources.getResPath());
        delfile.delete();
        if (delfile.exists()) {
            code = CodeMsg.Code_ERROR;
        } else {
            Integer flag = resourcesService.delresources(resid);
            if (flag > 0) {
                code = CodeMsg.Code_SUCCESS;
            } else {
                code = CodeMsg.Code_ERROR;
            }
        }
        Map<String, Object> back = new HashMap<>();
        back.put("code", code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/uploaddocument")
    public Map<String,Object> testupload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("filecategoryid") Integer filecategoryid,
                                         @RequestParam("filename") String filename,@RequestParam("uploader")String uploader,
                                         HttpServletRequest request) {
        Resources resources = new Resources();
        resources.setResCategoryid(filecategoryid);
        resources.setResLastuploader(uploader);
        String path = request.getSession().getServletContext().getRealPath("resources/data/document");
        File filePath = new File(path);
//        System.out.println("文件保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
//            System.out.println("目录不存在，创建目录：" + filePath);
            filePath.mkdirs();
        }

        //获取原始文件名称
        String originalFileName = multipartFile.getOriginalFilename();
//        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//        System.out.println("文件类型：" + type);

        //设置文件新名字
//        String fileName = System.currentTimeMillis() + "." + type;
        String fileName = filename + "." + type;
        resources.setResName(filename+"."+type);
        resources.setResPath(path+File.separator+filename+"."+type);

//        System.out.println("文件新名称：" + fileName);
        //在指定路径创建一个文件
        File targetFile = new File(path, fileName);


        Map<String,Object> back = new HashMap<>();
        //将文件保存到服务器指定位置
        try {
            multipartFile.transferTo(targetFile);
            Integer flag = resourcesService.addresource(resources);
            code = CodeMsg.Code_SUCCESS;
            return back;
        } catch (IOException e) {
            System.out.println("保存文件错误...");
            e.printStackTrace();
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/downresources")
    public ResponseEntity<byte[]> downresources(@RequestBody Map map, HttpServletRequest request) throws Exception {
        try {
            //下载路径
//            String path = request.getSession().getServletContext().getRealPath("resources/data/document");
            Integer resid = (Integer) map.get("resid");
            Resources resources = resourcesService.getresourcebyid(resid);
//            System.out.println(path+File.separator+fileName);
            String path = resources.getResPath();
            File file = new File(path);
            HttpHeaders headers = new HttpHeaders();
            //解决文件名中文乱码问题
            String downloadFileName = new String(resources.getResName().getBytes("UTF-8"), "iso-8859-1");
            //告诉浏览器以"attachment"方式打开文件
            headers.setContentDispositionFormData("attachment", downloadFileName);
            //设置请求头的媒体格式类型为 application/octet-stream(二进制流数据)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            Integer flag =  resourcesService.updatedownnum(resid);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件下载出错...");
            return null;
        }
    }


}
