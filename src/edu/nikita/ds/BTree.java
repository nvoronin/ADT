package edu.nikita.ds;

import java.util.*;

public class BTree {
	int value;
	
	BTree right;
	
	BTree left;
	
	BTree parent;
	
	public BTree(int val) {
		value = val;
	}
	
	public boolean hasChildren() {
		return right != null || left != null;
	}
	
	public void traverseInOrder() {
		if(left != null)
			left.traverseInOrder();
		
		System.out.print(" " + value + " ");
		
		if(right != null)
			right.traverseInOrder();
	}
	
	public void traversePreOrder() {
		System.out.print(" " + value + " ");
		
		if(left != null)
			left.traversePreOrder();
		
		if(right != null)
			right.traversePreOrder();
	}
	
	public void searchTreeInsert(int val) {
		if(val > value)
			if(right == null) 
				right = new BTree(val);
			else
				right.searchTreeInsert(val);
		else
			if(left == null) 
				left = new BTree(val);
			else
				left.searchTreeInsert(val);
	}
	
	public boolean depthMoreThenOne() {
		int[] rl = depth(0);
		return Math.abs(rl[0] - rl[1]) > 1;
	}
	
	private int[] depth(int accum) {
		
		int[] l = checkNodeDepth(accum, left);
		
		System.out.println("left -- " + l[0] + " - " + l[1]);
		
		if(Math.abs(l[1] - l[0]) > 1)
			return l;
		
		int[] r = checkNodeDepth(accum, right);
		
		if(Math.abs(r[1] - r[0]) > 1)
			return r;
		
		int[] result = new int[2];
		int rmax = (r[0] > r[1]) ? r[0] : r[1];
		int rmin = (r[0] < r[1]) ? r[0] : r[1];
		
		int lmax = (l[0] > l[1]) ? l[0] : l[1];
		int lmin = (l[0] < l[1]) ? l[0] : l[1];
		
		int max = rmax > lmax ? rmax : lmax;
		int min = rmin < lmin ? rmin : lmin;
		
		result[0] = max; 
		result[1] = min;
		
		return result;
	}
	
	private int[] checkNodeDepth(int accum, BTree tree){
		int[] l = null;
		if(tree == null) {
			l = new int[2];
			l[0] = accum;
			l[1] = accum;
		}
		else
			l = tree.depth(accum + 1);
		
		return l;
	}
	
	public static void build(int[] array, int start, int end, BTree root) {
		int m = ((end - start) / 2) + start;
		root.value = array[m];
		if(end - start > 0){
			root.left = new BTree(-1);
			root.right = new BTree(-1);
			build(array, start, m - 1, root.left);
			build(array, m + 1, end, root.right);
		}
	}
	
	public static BTree buildAgain(int[] array) {
		int start = 0;
		int end = array.length; 
		Stack<int[]> intervals = new Stack<int[]>();
		Stack<BTree> roots = new Stack<BTree>();
		BTree result = new BTree(array[array.length / 2]);
		roots.push(result);
		
		int[] in = new int[2];
		in[0] = 0;
		in[1] = array.length / 2 - 1;
		intervals.push(in);
		
		in = new int[2];
		in[0] = array.length / 2 + 1;
		in[1] = array.length - 1;
		intervals.push(in);
		
		while(!roots.isEmpty()){
			BTree root = roots.pop();
			if (root.left == null || root.right == null){
				if (root.right == null) {
					int[] rRange = intervals.pop();
					if(rRange[1] - rRange[0] > 1) {
						int m = rRange[1] - rRange[0];
						root.right = new BTree(array[m]);
						
						in = new int[2];
						in[0] = rRange[0];
						in[1] = rRange[0] + ((rRange[1] - rRange[0]) / 2) - 1;
						intervals.push(in);
						
						in = new int[2];
						in[1] = rRange[1];
						in[0] = rRange[0] + ((rRange[1] - rRange[0]) / 2) + 1;
						intervals.push(in);
						
						roots.push(root);
						roots.push(root.right);
					}
					else if(rRange[1] - rRange[0] == 1)
					{
						root.right = new BTree(rRange[0]);
						root.right.right = new BTree(rRange[1]);
					}
					else if(rRange[1] - rRange[0] == 0)
					{
						System.out.print("RIGHT");
						root.right = new BTree(rRange[0]);
					}
				}
				else if (root.left == null) {
					int[] l = intervals.pop();
					if(l[1] - l[0] > 1) {
						int m = l[1] - l[0];
						root.left = new BTree(array[m]);
						
						in = new int[2];
						in[0] = l[0];
						in[1] = l[0] + ((l[1] - l[0]) / 2) - 1;
						intervals.push(in);
						
						in = new int[2];
						in[1] = l[1];
						in[0] = l[0] + ((l[1] - l[0]) / 2) + 1;
						intervals.push(in);
						
						roots.push(root);
						roots.push(root.left);
					}
					else if(l[1] - l[0] == 1)
					{
						root.left = new BTree(l[0]);
						root.left.left = new BTree(l[1]);
					}
					else if(l[1] - l[0] == 0)
					{
						System.out.print("LEFT");
						root.left = new BTree(l[0]);
					}
				}
			}
		}
		
		return result;
	}
	
	public void getLayers(int layerNum, ArrayList<LinkedList<BTree>> layers) {
		if(layers.size() < layerNum + 1){
			layers.add(new LinkedList<BTree>());
		}
		layers.get(layerNum).add(this);
		if(left != null)
			left.getLayers(layerNum + 1, layers);
		if(right != null)
			right.getLayers(layerNum + 1, layers);
	}
	
	public BTree nextSibling(){
		int level = 1;
		
		if(parent == null)
			throw new IndexOutOfBoundsException();
		
		HashSet<BTree> visisted = new HashSet<BTree>();
		visisted.add(this);
		BTree node = parent;
		while(level != 0){
			if(node.left != null && !visisted.contains(node.left))
			{
				// go down left
				level--;
				node = node.left;
			}
			else if(node.right != null && !visisted.contains(node.right))
			{
				// go down right
				level--;
				node = node.right;
			}
			else {
				// go up
				visisted.add(node);
				node = node.parent;
				level++;
			}
		}
		return node;
	}
	
	public static void siblingTest(){

		BTree n8 = new BTree(8);
		BTree n7 = new BTree(7);
		BTree n6 = new BTree(6);
		BTree n5 = new BTree(5);
		BTree n4 = new BTree(4);
		BTree n3 = new BTree(3);
		BTree n2 = new BTree(2);
		BTree n1 = new BTree(1);
		
		n2.setLeft(n1);
		n3.setLeft(n2);
		n4.setLeft(n3);
		n4.setRight(n5);
		n5.setLeft(n6);
		n5.setRight(n7);
		n7.setLeft(n8);
		
		System.out.println("1 -> " + n1.nextSibling().value);
		System.out.println("2 -> " + n2.nextSibling().value + " -> " + n2.nextSibling().nextSibling().value);
		
		//System.out.println(" 6 & 8 " + findCommonAncestor(n6, n8).value);
	}

	private void setLeft(BTree n1) {
		n1.parent = this;
		this.left = n1;
	}
	
	private void setRight(BTree n1) {
		n1.parent = this;
		this.right = n1;
	}
	
	public ArrayList<ArrayList<BTree>> getSums(int sum) {
		// find a root that < sum
		
		//HashSet<BTree> possibleRoots = new HashSet<BTree>();
		//findPossibleRoots(this, possibleRoots, sum);
		
		return getPaths(this, new ArrayList<BTree>(), sum, sum);
	}
	
	private static ArrayList<ArrayList<BTree>> getPaths(BTree node,
			ArrayList<BTree> pathTo, int sum, int originalSum) {
		int theRest = sum - node.value;
		
		ArrayList<ArrayList<BTree>> rr = new ArrayList<ArrayList<BTree>>();
		
		if(theRest > 0)
		{
			ArrayList<BTree> list = (ArrayList<BTree>)pathTo.clone();
			list.add(node);
			
			if(node.left != null && node.left.value <= theRest)
				rr.addAll(getPaths(node.left, list, theRest, originalSum));
			
			if(node.right != null && node.right.value <= theRest)
				rr.addAll(getPaths(node.right, list, theRest, originalSum));
		}
		
		if(theRest == 0){
			ArrayList<BTree> r = (ArrayList<BTree>)pathTo.clone();
			r.add(node);
			rr.add(r);
		}
		
		if(node.left != null)
			rr.addAll(getPaths(node.left, new ArrayList<BTree>(), originalSum, originalSum));
		
		if(node.right != null)
			rr.addAll(getPaths(node.right, new ArrayList<BTree>(), originalSum, originalSum));
		
		return rr;
	}
	
	public static void testFindSum(){
		int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		BTree t = new BTree(-1);
		build(a, 0, a.length-1, t);
		
		t.traverseInOrder();
		
		ArrayList<ArrayList<BTree>> r = t.getSums(10);
		System.out.println("__________");
		System.out.println("Total: " + r.size());
		for(ArrayList<BTree> p : r){
			for(BTree n : p)
				System.out.print(n.value + " + ");
			System.out.println();
		}
	}

	private void findPossibleRoots(BTree node,
			HashSet<BTree> possibleRoots, int sum) {
		
		if(node.value == sum)
			possibleRoots.add(node);
		
		if(node.left != null){
			if((node.value + node.left.value) <= sum)
				possibleRoots.add(node);
			else
				findPossibleRoots(node.left, possibleRoots, sum);
		}
			
		if(node.right != null){
			if((node.value + node.right.value) <= sum)
				possibleRoots.add(node);
			else
				findPossibleRoots(node.right, possibleRoots, sum);
		}
	}

	enum Side{
		RIGHT,
		LEFT
	}
	
//	public static BTree findCommonAncestor(BTree t, BTree a, BTree b) {
//		if(a.equals(b))
//			return a;
//		
//		BTree n = t;
//		while (true){
//			Side as = GetSide(t, a);
//			Side bs = GetSide(t, b);
//			
//			if(as != bs)
//				break;
//			
//			if (as == Side.LEFT)
//				n = n.left;
//			
//			if (as == Side.RIGHT)
//				n = n.left;
//		}
//		
//		return n;
//	}

//	private static Side GetSide(BTree t, BTree b) {
//		if(t.left == null)
//			return Side.RIGHT;
//		
//		if(t.right == null)
//			return Side.LEFT;
//		
//		Side r = Side.RIGHT;
//		Stack<BTree> unvisisted = new Stack<BTree>();
//		unvisisted.add(t.left);
//		while(!unvisisted.isEmpty())
//		{
//			BTree node = unvisisted.pop();
//			if(node.equals(b))
//				
//		}
//		return r;
//	}

	private static int getLevel(BTree b) {
		int level = 0;
		BTree node = b;
		while (node.parent != null){
			level++;
			node = node.parent;
		}
		return level;
	}
}
