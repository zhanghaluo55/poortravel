package com.hongpro.poortravel.service.upload.controller;

import com.hongpro.poortravel.service.upload.fastdfs.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UploadController {
    @Value("${fastdfs.base.url}")
    private String FASTDFS_BASE_URL;

    @Autowired
    private StorageService storageService;

    /**
     * 文件上传
     *
     * @param dropFile    Dropzone
     * @param editorFiles wangEditor
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(@RequestParam("file") MultipartFile dropFile, @RequestParam("files") MultipartFile[] editorFiles) {
        Map<String, Object> result = new HashMap<>();
        System.out.println(dropFile);
        // Dropzone 上传
        if (dropFile != null) {
            result.put("fileName", writeFile(dropFile));
        }
        // wangEditor 上传
        //if (editorFiles != null && editorFiles.length > 0)
        else {
            List<String> fileNames = new ArrayList<>();

            for (MultipartFile editorFile : editorFiles) {
                fileNames.add(writeFile(editorFile));
            }
            System.out.println("fileNames:" + fileNames);
            result.put("errno", 0);
            result.put("data", fileNames);
        }

        System.out.println("upload service");

        return result;
    }

    /**
     * 将图片写入指定目录
     *
     * @param multipartFile
     * @return 返回文件完整路径
     */
    private String writeFile(MultipartFile multipartFile) {
        // 获取文件后缀
        String oName = multipartFile.getOriginalFilename();
        String extName = oName.substring(oName.lastIndexOf(".") + 1);

        // 文件存放路径
        String url = null;
        try {
            String uploadUrl = storageService.upload(multipartFile.getBytes(), extName);
            url = FASTDFS_BASE_URL + uploadUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回文件完整路径
        return url;
    }
}
