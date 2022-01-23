package notepad;

import javax.swing.*;

class Node
{
    String data;
    Node next;
    Node prev;

    Node(String data)
    {
        this.data = data;
        next = prev = null;
    }

}

public class Stack extends JFrame {

    Node top;
    Node tail;

    public void push(String data)
    {
        Node newNode = new Node(data);
        if(top == null)
        {
            top = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;

        }
    }

    public String redo()
    {
        if(tail != null)
        {
            if(tail.next != null)
            {
                String text = tail.next.data;
                tail = tail.next;
                return text;
            }
            else
            {
                String text = tail.data;
                return text;
            }
        }
        else
        {
            String text = tail.data;
            return text;
        }
    }
    public String undo(){
        if(tail != null)
        {
            if(tail.prev != null)
            {
                String text = tail.prev.data;
                tail = tail.prev;
                return text;
            }
            else {

                String text = "";
                return text;
            }
        }
        else
        {
            String text = "";
            return text;
        }
    }


}
