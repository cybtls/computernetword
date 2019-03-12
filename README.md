# computernetword
后台代码
#### 后端状态码

```java
package com.cyb.codemsg;

public class CodeMsg {
    /**
     * 调用接口成功
     */
    public static Integer Code_SUCCESS = 200;
    public static String Code_SUCCESS_MSG = "调用接口成功";


    /**
     * 调用接口失败
     */
    public static Integer Code_ERROR = 400;
    public static String Code_ERROE_MSG = "调用接口失败";


    /**
     *  存在
     */
    public static Integer Code_EXIST = 201;
    public static String Code_EXIST_MSG = "存在";

    /**
     * 账号状态为封禁
     */
    public static Integer Code_CLOSURE = 202;
    public static String Code_CLOSURE_MSG = "账号状态为封禁";


    /**
     * 账号不存在，登录失败
     */
    public static Integer Code_NOTEXIST =  204;
    public static String Code_NOTEXIST_MSG = "账号不存在，登录失败";

}

```

#### 后端解决跨域

##### pom.xml引入依赖

```xml
    <dependency>
      <groupId>com.thetransactioncompany</groupId>
      <artifactId>java-property-utils</artifactId>
      <version>1.9.1</version>
    </dependency>

    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.8.0</version>
    </dependency>

    <dependency>
      <groupId>com.thetransactioncompany</groupId>
      <artifactId>cors-filter</artifactId>
      <version>2.5</version>
    </dependency>
```

##### web.xml文件添加以下代码

```xml
  <filter>
    <filter-name>CORS</filter-name>
    <filter-class>com.thetransactioncompany.cors.CORSFilter</filter-class>
    <init-param>
      <param-name>cors.allowOrigin</param-name>
      <param-value>*</param-value>
    </init-param>
    <init-param>
      <param-name>cors.supportedMethods</param-name>
      <param-value>GET, POST, HEAD, PUT, DELETE</param-value>
    </init-param>
    <init-param>
      <param-name>cors.supportedHeaders</param-name>
      <param-value>Accept, Origin, X-Requested-With, Content-Type, Last-Modified</param-value>
    </init-param>
    <init-param>
      <param-name>cors.exposedHeaders</param-name>
      <param-value>Set-Cookie</param-value>
    </init-param>
    <init-param>
      <param-name>cors.supportsCredentials</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CORS</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

#### 解决前端传到后端的JSON对象转换

```java
//    查询成绩
    @ResponseBody
    @RequestMapping("/getmyscore")
    public List<Score> getmyscore(@RequestBody Map map){
		//以pojo类来接收
        JSONObject jsonobject = JSONObject.fromObject(map.get("User").toString());
        Student student = (Student)JSONObject.toBean(jsonobject,Student.class);
        System.out.println("学生账号" + student.getStuAccount());
        return null;
    }
```

#### 分页的实现（插件）

https://github.com/pagehelper/Mybatis-PageHelper

##### 添加依赖

```xml
    <!--mybatis分页依赖-->
    <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>5.1.2</version>
    </dependency>
```

##### 代码实现（结合前端看）

```java
private Integer pageSize = 10;
//    查询成绩
    @ResponseBody
    @RequestMapping("/getmyscore")
    public Map<String,Object> getmyscore(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("User").toString());
        Student student = (Student)JSONObject.toBean(jsonobject,Student.class);
        Integer stuId = student.getStuId();
        Integer pageNo = (Integer)map.get("CurrentpageNum");
        //查询总数并返回给前端
        Integer count = scoreService.getcount(stuId,null);
        //跟在PageHelper后面的第一个查询会进行分页 pageNo：起始页  pageSize：每页数量
        PageHelper.startPage(pageNo, pageSize);
        List<Score> score =  scoreService.getmyscore(stuId);
        if(score == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        Map<String,Object> back = new HashMap<>();
        back.put("myscore",score);
        back.put("count",count);
        back.put("code",code);
        return back;
    }
```



#### 文件上传与下载（例子）

##### 上传

```java
    @ResponseBody
    @RequestMapping("/rr")
    public String testupload(@RequestParam("file")MultipartFile multipartFile, HttpServletRequest request) {
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
```



##### 下载

```java
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
```

#### 文件上传与下载（自己的例子，要与前端记录.md结合着看）

参考博文https://tycoding.cn/2018/05/31/Spring-6/#more

##### 关于配置

###### 安装依赖

```xml
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.3</version>
    </dependency>
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.4</version>
    </dependency>
```

###### 配置文件spring-servlet.xml

```xml
    <!--文件上传解析器
        ID必须是multipartResolver-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!--定义最大上传大小  单位是bytes-->
        <property name="maxUploadSize" value="104857600" />
        <!--指定上传编码-->
        <property name="defaultEncoding" value="UTF-8"/>
        <!--单个文件最大上传大小-->
        <property name="maxUploadSizePerFile" value="20000000000"/>
    </bean>
```

##### 上传（后端接收前端的文件）

```java
    @ResponseBody
    @RequestMapping("/uploaddocument")
//下面@RequestParam里面的名字要与前端对应
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
            //这里是将文件名字，路径等数据保存到数据库
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
```

##### 下载（将数据返回给前端解析，告诉前端去下载）

```java
    @ResponseBody
    @RequestMapping("/downresources")
    public ResponseEntity<byte[]> downresources(@RequestBody Map map, HttpServletRequest request) throws Exception {
        try {
            //下载路径
//            String path = request.getSession().getServletContext().getRealPath("resources/data/document");
            //获取前端提交的资源id
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
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件下载出错...");
            return null;
        }
    }
```

