package com.neu.fac.utils;

import java.io.*;

public class DataUtils {


    public static String readData(String fileName){
        StringBuffer sb = null;
        BufferedReader br = null;
        String path = DataUtils.class.getResource("/").getPath() + fileName + ".txt";
        try {
            sb = new StringBuffer();
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null){
                sb.append(s + "/");
                s = br.readLine();
            }
            return sb.toString();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (br!=null){
                try {
                    br.close();
                }
                catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void writeData(String fileName,String json) throws IOException{
        String path = DataUtils.class.getResource("/").getPath()+fileName+".txt";
        BufferedWriter bufferedWriter = null;
        File file = new File(path);
        bufferedWriter = new BufferedWriter(new FileWriter(file,true));
        bufferedWriter.write(json);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        if(bufferedWriter != null){
            bufferedWriter.close();
        }
    }
    public static void deleteData(String fileName) throws IOException{
        String path = DataUtils.class.getResource("/").getPath()+fileName+".txt";
        BufferedWriter bufferedWriter = null;
        File file = new File(path);
        file.delete();
    }
}
