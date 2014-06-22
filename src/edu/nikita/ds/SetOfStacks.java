package edu.nikita.ds;

import java.util.*;


public class SetOfStacks {
	private ArrayList<Stack<Integer>> list = new ArrayList<Stack<Integer>>();
	
	public int maxSize = 0;
	
	public SetOfStacks(int size){
		maxSize = size;
		list.add(new Stack<Integer>());
	}
	
	public void push(int val){
		Stack<Integer> last = list.get(list.size() - 1);
		if(last.size() == maxSize ) {
			list.add(new Stack<Integer>());
			push(val);
		}
		else
			last.push(val);
	}
	
	public int pop(){
		Stack<Integer> last = list.get(list.size() - 1);
		if(last.isEmpty()) {
			list.remove(list.size() - 1);
			return pop();
		}
		else
			return last.pop();
	}
	
	public int popAt(int index){
		if(index < 0 || list.size() <= index)
			throw new IndexOutOfBoundsException();
		
		Stack<Integer> last = list.get(index);
		if(last.isEmpty()) {
			list.remove(index);
			return popAt(index);
		}
		else
			return last.pop();
	}
}
