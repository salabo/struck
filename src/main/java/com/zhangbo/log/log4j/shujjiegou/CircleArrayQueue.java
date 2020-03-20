package com.zhangbo.log.log4j.shujjiegou;

import java.util.Scanner;

/**
 * @author zhangbo
 * 利用取模实现环形数组
 * 总是比原数据少1
 * ${Date} ${TIme}
 */
public class CircleArrayQueue {

    //front总是指向第一个元素 rear指向最后一个元素的后?一个位置(因为希望空出一个作为约定)。初始值都是0
    //队列满的条件是 (rear+1)%maxSize = front
    //空的条件front=rear
    //有效数据 (rear+maxSize-front)%maxSize

    private int maxSize;

    private int head;

    private int end;

    private int arr [];

    public CircleArrayQueue(int size){
        this.maxSize = size;
        arr  = new int[size];
    }

    public boolean isFull(){
        return (end+1) % maxSize == head;
    }

    public boolean isEmpty(){
        return end == head;
    }

    public void add(int data){
        if (isFull()){
            System.out.println("满了");
            return;
        }
        arr[end] = data;
      end = (end +1)%maxSize;
    }

    public int getData(){

        if (isEmpty()){
            throw new RuntimeException("empty");
        }
        //思路: 1先把front存入临时变量2.再将front后移，但是有可能已经到最顶上了下面还有数据。所以考虑取模 3.将临时数据返回
        int temp = arr[head];
        head = (head +1)%maxSize;
        return temp;
    }

    public void showArr(){
        if (isEmpty())
            System.out.println("空的");
        //
        for (int i = head; i < head + (end + maxSize -head)%maxSize; i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    public int shouHead(){
        if (isEmpty())
            return -100;
        return arr[head];
    }
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
//        queue.add(1);
//        System.out.println(queue.end+"|"+queue.head);
//        queue.add(3);
//        System.out.println(queue.end+"|"+queue.head);
//
//        queue.add(2);
//        System.out.println(queue.end+"|"+queue.head);
//
//        queue.showArr();
//        int c =0;
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showArr();
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
                    int i = queue.shouHead();
                    System.out.println(i);
                default:
                    break;

            }
        }
    }

}
