import java.io.IOException;

public class HashChain {
  private SortedList[] hashArray; 

  private int arraySize;

  public HashChain(int size) {
    arraySize = size;
    hashArray = new SortedList[arraySize];
    for (int i = 0; i < arraySize; i++)
      hashArray[i] = new SortedList(); 
  }

  public void displayTable() {
    for (int j = 0; j < arraySize; j++) {
      System.out.print(j + ". "); 
      hashArray[j].displayList(); 
    }
  }

  public int hashFunc(int key) {
    return key % arraySize;
  }

  public void insert(Link theLink) {
    int key = theLink.getKey();
    int hashVal = hashFunc(key); 
    hashArray[hashVal].insert(theLink); 
  }

  public void delete(int key) {
    int hashVal = hashFunc(key); // hash the key
    hashArray[hashVal].delete(key); 
  }

  public Link find(int key) {
    int hashVal = hashFunc(key); // hash the key
    Link theLink = hashArray[hashVal].find(key); // get link
    return theLink;
  }

  public static void main(String[] args) throws IOException {
    int aKey;
    Link dataItem;
    int size, initSize, keysPerCell = 100;
    size = 100;
    initSize = 10;
    HashChain hashTable = new HashChain(size);

    for (int i = 0; i < initSize; i++){
      aKey = (int) (java.lang.Math.random() * keysPerCell * size);
      dataItem = new Link(aKey);
      hashTable.insert(dataItem);
    }
    hashTable.displayTable();
    aKey = 100;
    dataItem = new Link(aKey);
    hashTable.insert(dataItem);
    aKey = 100;
    hashTable.delete(aKey);

    aKey = 50;
    dataItem = hashTable.find(aKey);
    if (dataItem != null)
      System.out.println("Found " + aKey);
    else
      System.out.println("Could not find " + aKey);
  }

}
