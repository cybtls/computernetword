package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Resources;
import com.cyb.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String,Object> getresources(@RequestBody Map map){
        Integer categoryid = (Integer) map.get("categoryid");
        String resourcename = (String) map.get("resourcename");
        Map<String,Object> back = new HashMap<>();
        List<Resources> resources = resourcesService.getresources(categoryid,resourcename);
        if (resources == null){
            code = CodeMsg.Code_NOTEXIST;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("resources",resources);
        }
        back.put("code",code);
        return  back;
    }

    @ResponseBody
    @RequestMapping("/uploaddocument")
    public Map<String,Object> testupload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("filecategoryid") Integer filecategoryid,
                                         @RequestParam("filename") String filename,@RequestParam("uploader")String uploader,
                                         HttpServletRequest request) {
        Resources resources = new Resources();
        resources.setResCategoryid(filecategoryid);
        resources.setResLastuploader(uploader);
        resources.setResName(filename);
        String path = request.getSession().getServletContext().getRealPath("resources/data/document");
        File filePath = new File(path);
//        System.out.println("文件保存路径：" + path);
        resources.setResPath(path);
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

}
