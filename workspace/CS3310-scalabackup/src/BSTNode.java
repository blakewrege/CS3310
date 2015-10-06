

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 * These are the nodes in our Binary Search Tree.
 */
public class BSTNode {
    private short leftPointer;
    private short rightPointer;
    private String keyValue;
    private Country restOfData;

    public BSTNode() {
        this.leftPointer = -1;
        this.rightPointer = -1;
        this.keyValue = "";
        this.restOfData = null;
    }

    public BSTNode(String key, Country data) {
        this.leftPointer = -1;
        this.rightPointer = -1;
        this.keyValue = key;
        this.restOfData = data;
    }

    public BSTNode(short left, short right, String key, Country data) {
        this.leftPointer = left;
        this.rightPointer = right;
        this.keyValue = key;
        this.restOfData = data;
    }

    public short getRightPointer() {
        return rightPointer;
    }

    public void setRightPointer(short rightPointer) {
        this.rightPointer = rightPointer;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Country getRestOfData() {
        return restOfData;
    }

    public void setRestOfData(Country restOfData) {
        this.restOfData = restOfData;
    }

    public short getLeftPointer() {
        return leftPointer;
    }

    public void setLeftPointer(short leftPointer) {
        this.leftPointer = leftPointer;
    }
}
