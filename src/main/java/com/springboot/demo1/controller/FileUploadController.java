package com.springboot.demo1.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * 文件上传拦截
 */
@Controller
@Slf4j
public class FileUploadController
{

    //添加日志
    //使用@Slf4j注解来代替下面的
//    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @GetMapping("/upload")
    public String upload()
    {
        return "/upload";
    }

    @RequestMapping("/batchUpload")
    public String batchUpload()
    {
        return "/batchUpload";
    }

    /**
     * 模拟上传单个文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file)
    {
        if(!file.isEmpty())
        {
            String orgFileName = file.getOriginalFilename();
            String name = file.getName();
            String type = file.getContentType();
            long size = file.getSize();
            System.out.println("orgFileName:" + orgFileName);
            System.out.println("name:" + name);
            System.out.println("type:" + type);
            System.out.println("size:" + size);

            //打印日志
//            logger.info("=====" + orgFileName);
//            logger.info("=====" + type);
//            logger.info("=====" + size);

            //添加@Slf4j后会自动生成，但需要安装lombok插件
            log.info("=====" + orgFileName);
            log.info("=====" + type);
            log.info("=====" + size);

            try {
                File newFile = new File("F:\\workspaces\\workspace-idea\\" + orgFileName);
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
                return "upload fail! " + e.getMessage();
            }
        }
        else
        {
            return "upload fail! file is empty";
        }

        return "success";
    }


    /**
     * 模拟上传多个文件
     * @return
     */
    @PostMapping("/upload/batch")
    @ResponseBody
    public String batchUpload(HttpServletRequest request)
    {
        List<MultipartFile> fileList = ((MultipartHttpServletRequest)request).getFiles("file");

        BufferedOutputStream out = null;
        MultipartFile multipartFile = null;
        for (int i = 0; i < fileList.size(); i++)
        {
            multipartFile = fileList.get(i);
            if(!multipartFile.isEmpty())
            {
                try {
                    out = new BufferedOutputStream(new FileOutputStream(new File(multipartFile.getOriginalFilename())));
                    out.write(multipartFile.getBytes());
                    out.flush();
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    return "batch upload fail";
                }

            }
            else
            {
                return "file is empty!";
            }

        }


        return "success";
    }

}
