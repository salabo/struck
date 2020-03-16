package com.zhangbo.log.log4j.shujjiegou;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * @author zhangbo
 *
 * 稀疏数组
 * ${Date} ${TIme}
 */
@SuppressWarnings("all")
public class spareArray {

    public static void main(String[] args) {
        int origin[][] = new int[11][11];
        origin[1][3]=1;
        origin[1][2]=2;
        origin[3][3]=1;

        int sum = 0;
        for (int row = 0; row < origin.length; row++) {
            for (int col = 0;col<origin.length;col++){
                if (origin[row][col] != 0){
                    sum++;
                }
            }
        }
        //常见稀疏数组
        int xishu [][] = new int[sum+1][3];
        xishu[0][0]=origin.length;
        xishu[0][1]=origin.length;
        xishu[0][2]=sum;

        //将有效值赋值在稀疏数组当中
        int count=1;
        for (int row = 0; row < origin.length; row++) {
            for (int col = 0;col<origin.length;col++){
                if (origin[row][col] != 0){
                    xishu[count][0]= row;
                    xishu[count][1]= col;
                    xishu[count][2]= origin[row][col];
                    count++;
                }
            }
        }
        try {
            array2File(xishu,"/Users/zhangbo/E-disk/shell/struck.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int[][] ints = sparseArrayFromIo();
//            for (int i = 0; i < ints.length; i++) {
//
//
//                    System.out.printf("%d\t%d\t%d\t",ints[i][0],ints[i][1],ints[i][2]);
//
//            }
            reFlashArray(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将稀疏数组回复为原数组
     * @param spareArray
     */
    public static void reFlashArray(int spareArray[][]){
        int arr [] [] = new int[spareArray[0][0]][spareArray[0][1]];
        for (int i = 1; i < spareArray.length; i++) {
                arr [spareArray[i][0]][spareArray[i][1]] = spareArray[i][2];
        }

        int c =0;
    }

    /**
     * 写入磁盘操作
     * @param array
     * @param path
     * @throws IOException
     */
    public static void array2File(int array[][],String path) throws IOException {
        File file = new File(path);
        if (!file.exists())
            file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for (int row = 0; row < array.length; row++) {
            for (int j=0;j<3;j++) {
                writer.write(array[row][j]);
            }
        }
        writer.flush();
        writer.close();
    }

    /**
     * 将稀疏数组从磁盘读入到内存
     * @return
     * @throws Exception
     */
    public static int[][] sparseArrayFromIo( ) throws Exception {

        FileReader reader = new FileReader("/Users/zhangbo/E-disk/shell/struck.txt");
        Map<Integer,Integer> map = new HashMap();
        int count=0;
        int data = 0;
        while ((data=reader.read())!=-1){
            map.put(count,data);
            count++;
        }
        if (map.size() == 0){
            return null;
        }
        int[][] sparseArray = new int[map.size()/3][3];
        int index =0;
        for(int i = 0;i < map.size()/3;i++) {
            for (int j = 0; j < 3; j++) {
                sparseArray[i][j] = map.get(index);
                index ++;
            }
        }
        map.clear();
        return sparseArray;
    }

}
