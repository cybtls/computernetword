package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Video;
import com.cyb.service.VideoService;
import com.github.pagehelper.PageHelper;
import org.aspectj.apache.bcel.classfile.Code;
import org.omg.CORBA.PUBLIC_MEMBER;
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
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    private Integer code;

    private Integer pageSize = 10;

    @ResponseBody
    @RequestMapping("/getvideo")
    public Map<String,Object> getallvideo(@RequestBody Map map){
        Integer categoryid = (Integer) map.get("categoryid");
        String videoname = (String) map.get("videoname");
        Integer CurrentpageNum = (Integer) map.get("CurrentpageNum");
        Map<String, Object> back = new HashMap<>();
        Integer count = 0;
        List<Video> videos;
        if (categoryid == null && videoname != null) {
            count = videoService.getvideonum(null, videoname);
            PageHelper.startPage(CurrentpageNum, pageSize);
            videos = videoService.getvideo(null, videoname);
        } else if (categoryid != null && videoname == null) {
            count = videoService.getvideonum(categoryid, null);
            PageHelper.startPage(CurrentpageNum, pageSize);
            videos = videoService.getvideo(categoryid, null);
        } else if (categoryid != null && videoname != null) {
            count = videoService.getvideonum(categoryid, videoname);
            PageHelper.startPage(CurrentpageNum, pageSize);
            videos = videoService.getvideo(categoryid, videoname);
        } else {
            count = videoService.getvideonum(null, null);
            PageHelper.startPage(CurrentpageNum, pageSize);
            videos = videoService.getvideo(null, null);
        }
        if (count > 0) {
            code = CodeMsg.Code_SUCCESS;
            back.put("count", count);
            back.put("videos", videos);
        } else {
            code = CodeMsg.Code_NOTEXIST;
        }
        back.put("code", code);
        return back;
    }


    @ResponseBody
    @RequestMapping("getvideobyname")
    public Map<String,Object> getvideobyname(@RequestParam("videoname")String videoname){
        Integer flag = videoService.getvideobyname(videoname);
        if (flag > 0){
            code = CodeMsg.Code_EXIST;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        Map<String,Object> back = new HashMap<>();
        back.put("code",code);
        return back;
    }

    @ResponseBody
    @RequestMapping("/uploadvideo")
    public Map<String,Object> testupload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("filecategoryid") Integer filecategoryid,
                                         @RequestParam("filename") String filename, @RequestParam("uploader")String uploader,
                                         HttpServletRequest request) {
        Video video = new Video();
        String path = request.getSession().getServletContext().getRealPath("resources/data/video");
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }

        //获取原始文件名称
        String originalFileName = multipartFile.getOriginalFilename();
//        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

        //设置文件新名字
        String fileName = filename + "." + type;

//        System.out.println("文件新名称：" + fileName);
//      在指定路径创建一个文件
        File targetFile = new File(path, fileName);

        //获取服务器资源路径,因为前端需要的是http://localhost:8080/
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+"/resources/data/video/"+filename+"."+type;
//        System.out.println(basePath);


        Map<String,Object> back = new HashMap<>();
        video.setVideoCategoryid(filecategoryid);
        video.setVideoName(fileName);
        video.setVideoPath(basePath);
        video.setVideoLastuploader(uploader);
        //将文件保存到服务器指定位置
        try {
            Integer flag = videoService.addvideo(video);
            if (flag > 0){
                multipartFile.transferTo(targetFile);
                code = CodeMsg.Code_SUCCESS;
            }else {
                code = CodeMsg.Code_ERROR;
            }
        } catch (IOException e) {
            System.out.println("保存文件错误...");
            e.printStackTrace();
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;
    }

    @ResponseBody
    @RequestMapping("/delvideo")
    public Map<String,Object> delvideo(@RequestParam("videoId")Integer videoid,@RequestParam("videoName")String videoName,HttpServletRequest request){
        Map<String,Object> back = new HashMap<>();
        String path = request.getSession().getServletContext().getRealPath("resources/data/video");
        File delfile = new File(path+"\\"+videoName);
        delfile.delete();
        if (delfile.exists()){
            code = CodeMsg.Code_ERROR;
        }else {
            Integer flag = videoService.delvideo(videoid);
            if (flag > 0) {
                code = CodeMsg.Code_SUCCESS;
            } else {
                code = CodeMsg.Code_ERROR;
            }
        }
        back.put("code",code);
        return back;
    }



}
