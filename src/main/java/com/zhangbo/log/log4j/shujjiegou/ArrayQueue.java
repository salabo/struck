package com.zhangbo.log.log4j.shujjiegou;

import sun.jvm.hotspot.opto.Node;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
public class ArrayQueue {

    private int maxSize;

    private int head;

    private int end;

    private int arr [];

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        head = -1;//头部位置 总是指向第一个数据的前面
        end =-1;
        arr = new int[this.maxSize];
    }

    public boolean isEmpty(){
        return head == end;
    }

    public boolean isFull(){
        return end == maxSize-1;
    }

    public void add(int data){
        if (isFull()){
            System.out.println("满了");
            return;
        }
        end++;
        arr[end] = data;
    }

    public int getData(){
        if (isEmpty()){
            throw new RuntimeException("empty");
        }
        head++;
        return arr[head];
    }

    public void  show(){
        if (isEmpty()){
            throw new RuntimeException("em");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]:%d",i,arr[i]);
        }
    }

    public int showHead(){
        if (isEmpty())
            System.out.println(" 空的");
        return arr[head+1];
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("请输入:");
                    int v = scanner.nextInt();
                    queue.add(v);
                    break;
                case 'g':
                    try {
                        int data = queue.getData();
                        System.out.println("取出了"+data);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    int i = queue.showHead();
                    System.out.println(i);
                    default:
                        break;

            }
        }
    }
}
