package com.zhangbo.log.log4j.shujjiegou;


/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
public class MyLinkedList {

    DataNodeInfo head = new DataNodeInfo(null,null);

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.add(new DataNodeInfo(1,"1"));
        linkedList.add(new DataNodeInfo(2,"2"));
        linkedList.add(new DataNodeInfo(3,"3"));
        int c =0;
    }

    private void add(DataNodeInfo node){
        DataNodeInfo temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;//temp此时就是最后一个元素
        }
         temp.next = node;
         node.prev = temp;
    }

}

class DataNodeInfo  {

    Integer id;

    String name;

    DataNodeInfo next;

    DataNodeInfo prev;

    public DataNodeInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

