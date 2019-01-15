package com.example.demouser.splanner;
public class LinkedListNode<T> {
    /**
     *@author Ranjani Ramanathan
     */

    protected T data;
    private LinkedListNode<T> next;
    /**
     * Sets the instance of data as the data passed in
     * @param data = the data passed in to the method
     */
    public void setData( T data ) {
        this.data = data;
        /**
         *Gets the instance of data
         */
    }
    public T getData() {
        return data;
    }

    /**
     * sets the instance of next equal to the node passed in
     * @param node= the node passed in
     */
    public void setNext( LinkedListNode<T> node ) {
        this.next = node;

    }
    /**
     * Returns the value of next to get the pointer to the next node
     */
    public LinkedListNode<T> getNext(){
        return next;


    }
    /**
     * Returns a String representation of this node.
     */
    public String toString() {
        return getData().toString();
    }

}