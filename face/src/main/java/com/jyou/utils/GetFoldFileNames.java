package com.jyou.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: RenXin
 * @Date: Created in 2018/6/19
 * @Description:
 */
public class GetFoldFileNames {
    public static List<String> getFileName(String filePath) {
        String path = filePath; // 路径
        List<String> faceList = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
        }
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");
            } else {
                faceList.add(fs.getName());
            }
        }
       return faceList;
    }
}
