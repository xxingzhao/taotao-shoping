package com.taotao.service.impl;

import com.taotao.common.util.FtpUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USER_NAME}")
    private String FTP_USER_NAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${FTP_BASE_URL}")
    private String FTP_BASE_URL;

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String originFileName = file.getOriginalFilename();
            String newName = IDUtils.genImageName() + originFileName.substring(originFileName.lastIndexOf("."));
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER_NAME,
                    FTP_PASSWORD, FTP_BASE_PATH,
                    imagePath, newName, file.getInputStream());
            if(!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "上传失败!");
            } else {
                resultMap.put("error", 0);
                resultMap.put("url", FTP_BASE_URL + imagePath + "/" + newName);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", 1);
            resultMap.put("message", "上传异常!");
        }
        return resultMap;
    }
}
