package conlists;

import java.util.AbstractList;
import java.util.LinkedList;


public class ConcateLists extends AbstractList {

private final LinkedList list1;
private final LinkedList list2;
public ConcateLists(){
	this.list1 = null;
    this.list2 = null;
}





public ConcateLists(LinkedList list1, LinkedList list2) {
	this.list1 = list1;
    this.list2 = list2;
}

@Override
public Object get(int index) {
    if (index < list1.size()) {
        return  list1.get(index);
    }
    return list2.get(index-list1.size());
}

@Override
public int size() {
    return list1.size() + list2.size();
}
}
