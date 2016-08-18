import java.security.InvalidParameterException;

/**
 * Created by jeff.miller on 8/17/16.
 * Data Type offering O(n) search, O(1)insert, O(n) delete
 */
public class LinkedList<T> {
    private Node head = null;
    private Node tail = null; //Keep track of tail, gives us O(1) insert
    private int counter = 0;

    /**
     * Created LinkedList Object with initial starting object
     */
    public LinkedList(T data){
        add(data);
    }

    /**
     * Create empty LinkedList
     */
    public LinkedList(){
    }

    /**
     * Get the size of the linkedlist
     */
    public int size(){
        return counter;
    }

    /**
     * Add an object to the end of the list
     */
    public void add(T data){
        if (this.head == null){
            this.head = new Node(data);
            this.tail = head;
        }
        else{
            Node newTail = new Node(data);
            this.tail.setNext(newTail);
            this.tail = newTail;
        }
    }

    /**
     *
     * @param data The object to delete
     * @return true if deleted,  false if could not be found
     */
    public boolean delete(T data){
        // Check head first
        if (this.head == null) return false;  //empty list
        if (this.head.compareTo(data) == 0){
            this.head = head.getNext();
            return true;
        }
        //Have to search entire list,  O(n)
        Node currentNode = this.head.getNext();
        Node lastNode = this.head;
        while(currentNode != null){
            if(currentNode.compareTo(data) == 0){
                //Check if its the tail,  need to maintain that pointer
                if(this.tail.compareTo(currentNode.getData()) == 0){
                    this.tail = lastNode;
                }
                lastNode.setNext(currentNode.getNext());
                return true;
            }
            lastNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return false;
    }


    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        Node loopNode = this.head;
        toReturn.append("[");
        while(loopNode != null){
            toReturn.append(loopNode.getData().toString()).append(",");
            loopNode = loopNode.getNext();
        }
        toReturn.append("]");

        return toReturn.toString();
    }

    /**
     *
     * An object that holds some piece of data and also a pointer to the next piece of data
     * (Singly linked list)
     */
    class Node<T> implements Comparable<T>{
        private Node nextNode = null;
        private T data = null;

        public Node(T data){
            this.data = data;
        }

        public boolean hasNext(){
            return (this.nextNode != null);
        }

        /**
         * Get the next node
         */
        public Node getNext(){
            return this.nextNode;
        }

        public void setNext(Node next){
            this.nextNode = next;
        }

        public T getData(){
            return this.data;
        }

        @Override
        /**
         * returns 0 this node matches the element passed in
         */
        public int compareTo(T element){
            if (this.data == element){
                return 0;
            }
            else{
                return 1;
            }

        }
    }

    public static void main(String[] args){
        LinkedList myLinked = new LinkedList();
        myLinked.add("A");
        myLinked.add("B");
        myLinked.add("C");
        myLinked.add("D");
        System.out.println(myLinked.toString());
        System.out.println(myLinked.size()+"");
        System.out.println(myLinked.delete("A"));
        System.out.println(myLinked.toString());
        System.out.println(myLinked.delete("C"));
        System.out.println(myLinked.toString() + " " + myLinked.size());
    }
}


