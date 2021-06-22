package com.codebaobao.controller.admin;

import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("api")
@Slf4j
public class FileDownLoadController {


    @Value("${virtual.images.path}")
    String virtualImagePath;


    // 单文件上传
    @RequestMapping(value = "/singleFile")
    @ResponseBody
    public Result<Object> uploadFile(@RequestParam("file") final MultipartFile file, final HttpServletRequest request) {

        if (file.isEmpty()) {
            return new Result(CodeMsg.create(10000, "上传的文件大小为空,请检查!!"));
        }

        //获取文件名称、后缀名、大小
        String fileName = file.getOriginalFilename();
        final String suffixName = fileName.substring(fileName.lastIndexOf("."));
        final long size = file.getSize();
        log.info("上传的文件名称为：[{}],文件后缀为：[{}],文件大小为：[{}]!!", fileName, suffixName, size);

        // 存储转换后文件名称
        fileName = UUID.randomUUID() + suffixName;
        log.info("转换后的文件名为：[{}]!!", fileName);

        final File dest = new File(this.virtualImagePath + fileName);
        //判断父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return Result.success(fileName);
        } catch (final IOException e) {
            log.error("上传文件过程中发生异常！", e);
        }
        return Result.error(CodeMsg.create(10000, "上传失败！！"));
    }


//    // 多文件上传
//    @PostMapping(value = "multiFileUpload")
//    @ResponseBody
//    public Result multiFileUpload(HttpServletRequest request) {
//
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
//
//        for (MultipartFile file : files) {
//
//            if (file.isEmpty()) {
//                return new Result(false, StatusCode.ERROR, "上传多个文件时,某个文件大小为空,请检查!!");
//            } else {
//
//                String fileName = file.getOriginalFilename();
//                String suffixName = fileName.substring(fileName.lastIndexOf("."));
//                long size = file.getSize();
//
//                log.info("上传的文件名称为：[{}],文件后缀为：[{}],文件大小为：[{}]!!", fileName, suffixName, size);
//
//                fileName = UUID.randomUUID() + suffixName;
//                log.info("转换后的文件名为：[{}]!!", fileName);
//
//                File dest = new File(filePath + fileName);
//                if (!dest.getParentFile().exists()) {
//                    dest.getParentFile().mkdir();
//                }
//
//                try {
//                    file.transferTo(dest);
//                } catch (IOException e) {
//                    log.error("上传文件过程中发生异常!!", e);
//                }
//
//            }
//        }
//        return new Result(true, StatusCode.OK, "上传成功！！");
//    }


}
