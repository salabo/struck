package com.zhangbo.log.log4j.shujjiegou;

import org.springframework.data.annotation.Transient;


/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
public class SingleLinkedList {


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

}

class DataNode{

    public Integer id;

    public String name;

    @Transient
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