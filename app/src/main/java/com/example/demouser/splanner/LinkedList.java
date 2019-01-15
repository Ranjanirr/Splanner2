package com.example.demouser.splanner;
public class LinkedList<T>{
    protected LinkedListNode<T> head;//is the first node
    protected T returnedData;
    /**
     * This adds a new piece of data at a given index place and links the nodes before
     * and after it
     * @param index
     * @param data = data passed in
     **/

    public void addForwards( int index, T data ) {
        //if it is empty and index is 0 (head should always be created at zero)
        //head = new LinkedListNode<T>();
        LinkedListNode<T> node2 = new LinkedListNode<T>();
        node2.setData(data);
        if (index == 0) {
            //makes head a new node, sets the head to data, and creates a temporary
            //added node variable

            node2.setNext(head);
            head = node2;


        }
        //if there are more nodes already put after the current one
        else {
            LinkedListNode<T> addedNode = new LinkedListNode<T>();
            addedNode = head;
            //if the index is met
            int count = 0;
            while (count <index-1) {


                //keep adding a node and increasing the indexPlace, while recursing the
                //add method until when the index is met
                addedNode= addedNode.getNext();
                count ++;


            }
            if(count == index-1) {

                //links the new node to the old node's original next value
                node2.setNext(addedNode.getNext());

                //links the next next node after added node to the new one
                addedNode.setNext(node2);
            }
        }
    }




    /**
     * This gets the data stored at and index amount of nodes from the head
     * @param index
     **/
    public T getForwards( int index ) {
        //creates a new temporary variable
        LinkedListNode<T> gottenNode = head;
        //initializes the new node to the head
        //gottenNode = head;
        int indexPlace = 0;
        if(index == 0) {
            returnedData = gottenNode.getData();
        }
        else {
            /*Repeats if the index is not reached yet*/
            while (indexPlace<index) {

                /*Increments node and indexPlace, recurses the get method*/

                gottenNode = gottenNode.getNext();
                indexPlace ++;

            }
            /*If the index is met, the data returned is the data from the current node*/
            if(indexPlace == index) {
                returnedData = gottenNode.getData();
            }

        }


        return returnedData;// the data returned from the method

    }

    /**
     * This deletes the data index amount of nodes away from the head
     * @param index
     **/
    public void deleteForwards( int index ) {
        //creates a new temporary variable
        LinkedListNode<T> deletedNode = new LinkedListNode<T>();
        //initializes the new node to the head
        deletedNode = head;

        int indexPlace = 0;
        if(index == 0) {
            head= deletedNode.getNext();
        }
        else {

            while (indexPlace<index-1) {

                /*Increments indexPlace and sets the current node to the pointed node*/

                deletedNode = deletedNode.getNext();
                indexPlace ++;

            }
            /*If the index is met, the node currently pointed will point to the node after next*/
            if(indexPlace == index-1) {
                LinkedListNode<T> nextNode = deletedNode.getNext();
                LinkedListNode<T> afterNextNode = nextNode.getNext();
                deletedNode.setNext(afterNextNode);
            }

        }


    }

    /**
     *This recieves the amount of elements in the lists
     **/
    public int size() {
        LinkedListNode<T> countedNode = head;// starts the variable at head
        int sizeCount = 0;
        if (isEmpty()) {// if the array is empty, the size is zero
            sizeCount = 0;
        }
        while(countedNode.getNext()!= null){//keep counting if there are still elements
            sizeCount++;
            countedNode = countedNode.getNext();
        }
        if (countedNode.getNext()== null){//done counting
            sizeCount++;
            return sizeCount;
        }
        return sizeCount;

    }



    /**
     * Check if the list is empty.
     **/
    public boolean isEmpty() {
        if(head == null) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Returns a string representation of the list
     **/
    public String toString() {
        //initializes a new string
        String stringOfData = new String("");
        //
        LinkedListNode<String> toStringNode = new LinkedListNode<String>();
        toStringNode = (LinkedListNode<String>) head;
        if (toStringNode == null) {
            stringOfData = "";
        }
        else {


            while (toStringNode.getNext()!= null) {

                stringOfData = stringOfData + toStringNode.getData()+". ";
                toStringNode = toStringNode.getNext();


            } if (toStringNode.getNext() == null) {
                stringOfData = stringOfData + toStringNode.getData()+ ". ";

            }
        }
        return stringOfData;
    }
}
