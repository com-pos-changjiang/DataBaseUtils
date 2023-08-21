package com.FileUtils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyFileUtils {

    /**
     *
     * @param fileName 要读取的文件绝对路径
     * @return 读取文件的每行的内容 用list存储
     */
    public static List<String> getFileList(String fileName) {
        File file = new File(fileName);
        List<String> tableList = new ArrayList<>();
        if(!file.exists())
            System.out.println("文件不存在");
        else{
            try {
                String line = "";
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                while ((line=br.readLine())!=null){
                    tableList.add(line);
                }
                System.out.println("读取到的文件行数 ： "+tableList.size());
            }catch (FileNotFoundException e){

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tableList;
    }

    /**
     *
     * @param fileName 要写入的文件名
     * @param content  要写入的文件内容
     */
    public static void writeToFile(String fileName,String content){
        try {
            File file = new File(fileName);
            if(!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(fileName,true);
            fw.append(content);
            fw.flush();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
