package com.zhangbo.log.log4j.shujjiegou;

import org.springframework.data.annotation.Transient;

import java.util.Queue;
import java.util.Stack;


/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
public class SingleLinkedList {

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.add(new DataNode(1,"1"));
        list.add(new DataNode(2,"1"));
        list.add(new DataNode(3,"1"));
        list.add(new DataNode(4,"1"));
        System.out.println(list.getRelase(2));
//        list.reverse();
        list.reverseByStack();
        int c =0;
    }


    private DataNode head;

    public SingleLinkedList(){
        head = new DataNode(null, null);
    }

    public void add(DataNode node){
        //头结点不能动，需要一个辅助节点。
        DataNode temp = head;
        while (true) {
           if (temp.next == null){
               break;
           }
           //如果没有找到,则将temp后移
            temp = temp.next;
        }
        //当退出while的时候temp就指向了最后一个位置
        // ↓..   ↓
        // head one  two ....   end
        temp.next = node;
    }

    public void addByOrder(DataNode node)    {
        //头结点不能动，需要一个辅助节点。
        DataNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null){
                break;
            }
            if (temp.next.id > node.id){
                break;
            }
            else if (temp.next.id.equals(node.id)){
                flag = true;
                break;
            }
            //如果没有找到,则将temp后移
            temp = temp.next;
        }

        if (flag){
            throw new RuntimeException("already exist");
        } else {
            node.next = temp.next;//将原有链表的下一个节点 让新插入的节点连接
            temp.next =node;
        }
    }


    /**
     * 单链表
     * @param findx
     * @return
     */
    public DataNode get(int findx){
        int index = 0;//跳过头结点
        DataNode temp = head;
        while (true){
            if (temp.next == null){//如果头结点下没有 则为空
                throw new IndexOutOfBoundsException("the maxSize is "+index);
            }
            //移动下一个节点
            temp = temp.next;
            if (index == findx){
                return temp;
            }
            index++;

        }
    }

    public int size(){
        int size = 0;
        DataNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            size++;
            temp = temp.next;
        }
        return size;
    }

    /****
     * 根据id来删除
     *  单链表删除 找到上一个元素
     * @param data
     */
    public void delete(DataNode data){
        if (head.next == null){
            throw new IndexOutOfBoundsException();
        }
        DataNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.id.equals(data.id)){ //找到了当前元素
                //temp则为前面一个节点
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    /****
     * 查找单lian表的 倒数第k个元素
     * @param k
     * @return
     */
    public DataNode getRelase(int k){
        if (head.next == null){
            return null;
        }
        int result = size() - k;
        if (result<0){
            return null;
        }
        return get(result);
    }

    public void clear(){
        head.next = null;
    }

    public void reverseByStack(){
        if (head.next == null || head.next.next == null)
            return;
        Stack<DataNode> s = new Stack<>();
        DataNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            s.add(temp.next);
            temp = temp.next;
        }
        clear();
        DataNode revers = new DataNode(null,null);
        while (s.size() >0){
            revers.next = s.pop();
            revers = revers.next;
        }
    }

    /**
     * 将原有节点反转 @linked: https://www.bilibili.com/video/av59600020?p=22
     */
    public void reverse(){
        DataNode reverseNode = new DataNode(null,null);
//        DataNode temp = head;
//        DataNode temp1= null;
//        while (true){
//            if (temp.next == null){
//                break;
//            }
//
//            reverseNode.next = temp.next;
//
//            temp = temp.next;
//        }

        DataNode next = null;
        DataNode cur = head.next;//当前的元素
        while (cur != null) {
            next = cur.next;
            cur.next = reverseNode.next;
            reverseNode.next = cur;
            cur = next;
        }
        head.next = reverseNode.next;
    }


}

class DataNode{

    public Integer id;

    public String name;

    public DataNode next;

    public DataNode(Integer id,String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}