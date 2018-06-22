package com.jyou.controller;

import com.alibaba.fastjson.JSONObject;
import com.jyou.service.IFaceService;
import com.jyou.utils.DeleteFileUtils;
import com.jyou.utils.GetFoldFileNames;
import com.jyou.utils.ResultMap;
import methodFD.AFDFace;
import methodFR.AFRFace;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recognitionCorrelation.AFR_FSDK_FACEMODEL;
import recognitionCorrelation.FaceInfo;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author: RenXin
 * @Date: Created in 2018/6/19
 * @Description:
 */

@RestController
@RequestMapping("/face")
public class FaceController {
    @Resource
    private IFaceService faceService;


    //人脸存储路径path
    //public static final String  PATH;
   // private static final Logger logger = LoggerFactory.getLogger(FaceController.class);



    /**
     * 静态代码块 自动根据配置加载
     */
    static {
        Properties properties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("common.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*@RequestMapping("/test1")
    public car[] getTest(int id) {
        //String s = faceService.selectId(id);

         List<car> ches = new ArrayList();
         ches.add(new car(1,"zhansan"));
        ches.add(new car(2,"lisi"));
        List<car> carList = Collections.emptyList();
        return carList;
    }*/

    /**
     * 人脸识别(闸机)
     * @param faceBase64
     * @return
     */
    @RequestMapping("/faceInfo")
    public ResultMap faceInfo(@RequestBody String faceBase64) {

        String emptyPath = "";
        if (faceBase64.isEmpty()){
            return  ResultMap.getResultMap(400,"人像不能为空",emptyPath);
        }
        JSONObject jsonObject = JSONObject.parseObject(faceBase64);
        String faceInfo = jsonObject.getString("faceBase64");


        //解码
        byte[] data = new byte[0];
        try {

            BASE64Decoder decoder = new BASE64Decoder();
            data =  decoder.decodeBuffer(faceInfo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResultMap.getResultMap(400,"请传入正确的图片",emptyPath);
        }
        String path = "/mnt/faceZhaJi/"+ UUID.randomUUID().toString()+".jpg";
       // String path = "D:/aa2/"+ UUID.randomUUID().toString()+".jpg";
        if (data == null){
            return ResultMap.getResultMap(400,"face is null",emptyPath);
        }
        if(data.length<3||path.equals("")) return null;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
        FaceInfo[] faceInfos = new FaceInfo[0];
        try {
            faceInfos = AFDFace.doDetection(path);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.getResultMap(400,"图像对比出错");
        }
        String file = path;
        if (faceInfos.length > 1){
            DeleteFileUtils.deleteFile(file);
            return ResultMap.getResultMap(400,"人脸不合规",emptyPath);
        }else if (faceInfos.length == 0){
            DeleteFileUtils.deleteFile(file);
            return ResultMap.getResultMap(400,"人脸不合规",emptyPath);
        }
        return ResultMap.getResultMap(200,"人脸检测成功",path);
    }

    @RequestMapping("FRFace")
    public ResultMap FRFace(@RequestBody String faceBase64){
        JSONObject jsonObject = JSONObject.parseObject(faceBase64);
        String faceInfo = jsonObject.getString("faceBase64");
        byte[] data = new byte[0];
        try {
            // data = Base64.getDecoder().decode(faceBase64);
            BASE64Decoder decoder = new BASE64Decoder();
            data =  decoder.decodeBuffer(faceInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.getResultMap(400,"人脸base64错误");
        }
        String faceB ="/mnt/faceZhaJiDuiBi/"+ UUID.randomUUID().toString()+".jpg";
        // String faceB = "D:/aa3/"+UUID.randomUUID().toString()+".jpg";
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(faceB));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + faceB);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
        List<String> fileName = GetFoldFileNames.getFileName("/mnt/faceZhaJi/");
       // List<String> fileName = GetFoldFileNames.getFileName("D:/aa2/");
        int nmp  = 0;
        int code = 0;
        String resultMessage = null;
        for (int i = 0; i < fileName.size() ; i++) {
            String face_url = "/mnt/faceZhaJi/" + fileName.get(i);
            //String face_url = "D:/aa2/" + fileName.get(i);
            Float aFloat = AFRFace.doFaceRecognition(face_url, faceB);
            if (aFloat >= 0.6){
               // DeleteFileUtils.deleteFile(faceB);
                nmp += 1;
                code  = 200;
                resultMessage = "验证成功,请入园";
                break;
            }
        }
        if (nmp == 0){
            code = 201;
            resultMessage = "人脸库无匹配";
        }
        DeleteFileUtils.deleteFile(faceB);
        return  ResultMap.getResultMap(code, resultMessage);
        }



    }

