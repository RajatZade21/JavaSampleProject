package linkedlist;

class LinkList{
    Node head; // head of list

    /* Linked list Node*/
    static class Node{
        int data;
        Node next;

        // Constructor to create a new node
        // Next is by default initialized as null
        Node(int d){
            data = d;
        }
    }

    public static LinkList insert(LinkList linkList,int data){
        Node newNode = new Node(data);  // Create a new node with given data
        if(linkList.head == null){      // If the Linked List is empty,then make the new node as head
            linkList.head = newNode;
        }
        else{
            Node last = linkList.head;
            while (last.next != null){  // traverse till the last node and insert the new_node there
                last = last.next;
            }
            last.next = newNode;         // Insert the new_node at last node
        }
        return linkList;
    }

    public static LinkList deleteByKey(LinkList list,int key){
        Node curNode = list.head,prev = null; // Store head node

        if(curNode != null && curNode.data == key){           // If head node itself holds the key to be deleted
            list.head = curNode.next;
            System.out.println(key + " found and deleted");
            return list;
        }
        // Search for the key to be deleted,
        // keep track of the previous node as it is needed to change currNode.next
        while (curNode != null && curNode.data != key){
            prev = curNode;
            curNode = curNode.next;
        }

        // If the key was present, it should be at currNode
        // Therefore the currNode shall not be null
        if(curNode != null){
            prev.next = curNode.next;
            System.out.println(key + " found and deleted");
            return list;
        }

        // If key was not present in linked list currNode should be null
        if(curNode == null){
            System.out.println(key + " no found");
        }
        return list;
    }

    public static LinkList deleteAtPosition(LinkList list,int Index){
        Node curNode = list.head,prev = null;

        if(Index == 0 && curNode != null){
            list.head = curNode.next;
            System.out.println(curNode.data + " at " + Index + " deleted");
        }
        int counter = 0;
        while (curNode != null){
            if(counter == Index){
                prev.next = curNode.next;
                System.out.println(curNode.data + " at " + Index + " deleted");
                break;
            }
            else{
                prev = curNode;
                curNode = curNode.next;
                counter++;
            }
        }

        if(curNode == null){
            System.out.println(Index + " no found");
        }
        return list;
    }

    public static void printLinkList(LinkList list){
        Node curNode = list.head;
        while (curNode != null){
            System.out.println(curNode.data + " ");
            curNode = curNode.next;
        }
    }
}
public class LinkListExample {
    public static void main(String[] args){
        LinkList list = new LinkList();

        list = LinkList.insert(list,20);
        list = LinkList.insert(list,56);
        list = LinkList.insert(list,33);
        list = LinkList.insert(list,13);
        list = LinkList.insert(list,25);
        list = LinkList.insert(list,9);
        list = LinkList.insert(list,27);
        LinkList.printLinkList(list);

        LinkList.deleteByKey(list,27);
        LinkList.deleteByKey(list,13);
        LinkList.deleteByKey(list,1);

        LinkList.deleteAtPosition(list,2);
        LinkList.deleteAtPosition(list,25);
    }


}
