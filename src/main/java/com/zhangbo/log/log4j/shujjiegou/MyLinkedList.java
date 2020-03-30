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
        linkedList.update(new DataNodeInfo(1,"测试"));
        linkedList.delete(new DataNodeInfo(1,"2"));
        int c =0;
    }

    public void add(DataNodeInfo node){
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

    public void update(DataNodeInfo node){
        if (head.next == null)
            throw new RuntimeException("空的，无法修改");
        DataNodeInfo temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.id.equals(node.id)){//找到 此时temp.next就是node所在的原数据
                temp.next.name = node.name;
                break;
            }
            temp = temp.next;
        }
    }

    public void delete(DataNodeInfo data){
        if (head.next == null){
            throw new IndexOutOfBoundsException();
        }
        DataNodeInfo temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.id.equals(data.id)){ //找到了当前元素 temp.next为当前的元素
                //temp则为前面一个节点
                DataNodeInfo cur = temp.next;
                cur.prev.next =  cur.next;
                cur.next.prev = cur.prev;
//                temp.next.prev = temp.next.next;//当前元素的前一个 拼接到当前元素的下一个
//                temp.next.next = temp.next.prev;
                break;
            }
            temp = temp.next;
        }
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

