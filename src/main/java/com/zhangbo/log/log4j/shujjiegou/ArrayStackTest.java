package com.zhangbo.log.log4j.shujjiegou;

/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
public class ArrayStackTest {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.show();
    }
}

class ArrayStack {

    private int maxSize;

    private Object [] o; //数组模拟入栈出栈

    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        o = new Object[maxSize];
    }

    public void push(Object obj){
        if (isFull()){
            return;
        }
        o[++top] =obj;
    }

    public Object pop (){
        if (isEmpty()){
            throw new NullPointerException("empty");
        }
        Object temp = o[top];
        top --;
        return temp;
    }

    private boolean isEmpty(){
        return this.top == -1;
    }

    private boolean isFull(){
        return top == maxSize-1;
    }

    public void show(){
        for (int i = top;i>=0;i--){
            System.out.println(o[i]);
        }
    }

}