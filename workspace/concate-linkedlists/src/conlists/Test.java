package conlists;


import java.util.LinkedList;

public class Test {
	 public static void main(String[] args) {


	      LinkedList l1 = new LinkedList();
	      l1.add("aaa");
	      l1.add("bbb");
	      l1.add("ccc");
	      System.out.println();
	      LinkedList l2 = new LinkedList();
	      l1.add("zzz");
	      l1.add("yyy");
	      l1.add("xxx");
	      System.out.println(" LinkedList test");
	      System.out.println(l1 +""+ l2);
	     ConcateLists c1 = new ConcateLists(l2,l1);
	     System.out.println(c1);
	   }
	}
	


