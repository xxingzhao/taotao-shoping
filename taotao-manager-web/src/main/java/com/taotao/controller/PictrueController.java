package com.taotao.controller;

import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PictrueController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map<String, Object> pictureUpload(MultipartFile uploadFile) {
        Map<String, Object> resuultMap = pictureService.uploadFile(uploadFile);
        return resuultMap;
    }
}
