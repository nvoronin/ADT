package edu.nikita.ds;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
       /*
	   System.out.println(StringUtlis.IsUniqueChars("123456789")); 
	   System.out.println(StringUtlis.IsUniqueChars("aa"));
	   System.out.println(StringUtlis.reverse("abc"));
	   System.out.println(StringUtlis.reverse("1234"));
	   
	   
	   System.out.println(StringUtlis.areAnagrams("1122", "2121"));
	   System.out.println(StringUtlis.areAnagrams("1234", "1224"));
	   
	   System.out.println(StringUtlis.replaceSpace("AAA  B B B "));
	   
	   //System.out.println(StringUtlis.rotate("123456789".toCharArray(), 3));
	   
	   System.out.println(StringUtlis.isWordRotation("waterbottle", "erbottlewat"));
	   */

        //prev();
	   Maze maze = new Maze(20, 100);
       maze.generate(0, 0, false);
       maze.print();
       System.out.println();
       MazeWalker.memoryWalk(maze, 0, 0, new HashSet<Tuple>());
       maze.print();
	}

    private static void prev() {
        LinkedListNode<Integer> head = new LinkedListNode<Integer>(1);
        head.appendToTail(2).appendToTail(3).appendToTail(4).appendToTail(5);
        System.out.println(head);

        head.delete(3);
        System.out.println(head);
        head.appendToTail(2).appendToTail(3).appendToTail(2).appendToTail(3).appendToTail(5);
        head.removeDuplicates();
        System.out.println(head);
        head.appendToTail(2).appendToTail(3).appendToTail(2).appendToTail(3).appendToTail(5).appendToTail(3).appendToTail(2).appendToTail(3);
        head.removeDuplicatesInPlace();
        System.out.println(head);

        head = new LinkedListNode<Integer>(0);
        head.appendToTail(1).appendToTail(2).appendToTail(3).appendToTail(4).appendToTail(5);

        LinkedListNode<Integer> r = head.nth(2);
        System.out.println(r);

        System.out.println("Before: " + head);
        r.deleteMe();
        System.out.println("After: " + head);

        LinkedListNode<Integer> n1 = new LinkedListNode<Integer>(1);
        LinkedListNode<Integer> n2 = new LinkedListNode<Integer>(9);
        n2.appendToTail(9).appendToTail(9).appendToTail(9).appendToTail(9).appendToTail(9).appendToTail(9);

        LinkedListNode<Integer> sum  = LinkedListNode.sum(n1, n2);
        //System.out.println("n1: " + n1);
        //System.out.println("n2: " + n2);
        //System.out.println("n2: " + sum);


        head = new LinkedListNode<Integer>(0);
        r = head.appendToTail(1).appendToTail(11).appendToTail(2).appendToTail(3);
        r.appendToTail(4).appendToTail(5).setNext(r);

        //System.out.println(r.getValue() + " -- cerlce: " + head.findCercle().getValue());

        QSort q = new QSort();
        ArrayList<Character> a = new ArrayList<Character>();
        for(Character c : "doge likes wow".toCharArray()){
            a.add(c);
        }
        //q.quickSort(a, 0, a.size() - 1, new CustomComparer("wo"));
        Collections.sort(a, new CustomComparer("wo"));
        //System.out.println(" + " + QSort.print(a));


        ArrayStack aa = new ArrayStack();
        aa.push(1, 0);
        aa.push(2, 1);
        aa.push(3, 2);
        aa.push(11, 0);
        aa.push(111, 0);

        //System.out.println(" + " + aa.pop(0));

        LinkedStackNode s = new LinkedStackNode(5);
        s = s.push(9);
        s = s.push(3);
        s = s.push(8);

        //System.out.println("Min: "+ s.getMin());
        //System.out.println("pop: "+ s.pop());
        //System.out.println("pop: "+ s.pop());
        //System.out.println("Min: "+ s.getMin());

        SetOfStacks sos = new SetOfStacks(2);
        sos.push(0);
        sos.push(0);
        sos.push(1);
        sos.push(1);
        sos.push(2);
        sos.push(2);

        System.out.println("pop: "+ sos.pop());
        System.out.println("pop: "+ sos.popAt(1));
        System.out.println("pop: "+ sos.popAt(1));
        System.out.println("pop: "+ sos.popAt(1));

        Hanoi h = new Hanoi();
        //h.GoMagic();
        //System.out.println("Ok");

        java.util.Stack<Integer> myStack = new java.util.Stack<Integer>();
        myStack.push(1);
        myStack.push(3);
        myStack.push(2);
        myStack.push(4);

        //System.out.println(" OWO ");
        //LinkedStack.sort(myStack);
        // LinkedStack.printStackOnce(myStack);

        BTree root = new BTree(3);
        root.left = new BTree(2);
        root.left.left = new BTree(1);
        root.right = new BTree(5);
        root.right.left = new BTree(4);
        root.right.right = new BTree(6);

        //System.out.print(" In order");
        //root.traverseInOrder();
        //System.out.print(" pre order");
        //root.traversePreOrder();

        System.out.println();

        BTree bst = new BTree(5);
        bst.searchTreeInsert(4);
        bst.searchTreeInsert(6);
        //bst.traverseInOrder();

        //System.out.println("false = " + bst.depthMoreThenOne());


        bst.searchTreeInsert(3);
        bst.searchTreeInsert(2);
        bst.searchTreeInsert(8);
        bst.searchTreeInsert(1);

        //bst.traverseInOrder();

        //System.out.println("true = " + bst.depthMoreThenOne());

        System.out.println("____");

        int[] arrr = { 1,2,3 };
        BTree sbt2 = new BTree(-1);
        //BTree.build(arrr,0, arrr.length -1, sbt2);
        //sbt2.traverseInOrder();

        //ArrayList<LinkedList<BTree>> layers = new ArrayList<LinkedList<BTree>>();
        //root.getLayers(0, layers);
        //print(layers.get(0));
        //print(layers.get(1));
        //print(layers.get(2));

        //BTree.siblingTest();
        BTree.testFindSum();
    }

    static void print(LinkedList<BTree> ls){
    	System.out.println();
    	for(BTree node : ls)
    		System.out.print(node.value  + " -> ");
    }
}
