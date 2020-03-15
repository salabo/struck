package com.zhangbo.log.log4j.shujjiegou;

import javax.sound.midi.Soundbank;

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
        for (int i = 0; i < xishu.length; i++) {
            for (int j=0;j<3;j++){
                System.out.printf("%d\t",xishu[i][j]);
            }
            System.out.println();

        }

    }
}
