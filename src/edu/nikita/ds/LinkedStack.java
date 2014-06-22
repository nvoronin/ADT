package edu.nikita.ds;

import java.util.Stack;

public class LinkedStack {
	private LinkedStackNode top;
	
	public boolean IsEmpty() { 
		return top == null;
	}
	
	public void push(int val) {
		if(top == null)
			top = new LinkedStackNode(val);
		else
			top = top.push(val);
	}
	
	public int pop(){
		if(IsEmpty())
			return -1;
		else{
			if (top.isEmpty()){
				int last =  top.pop();
				top = null;
				return last;
			}
			else
				return top.pop();
		}
	}
	
	public static void sort(Stack<Integer> s) {
		Stack<Integer> t = new Stack<Integer>();
		t.push(s.pop());
		
		while(!s.isEmpty()){
			int current = s.pop();
			if(current > t.peek())
			{
				t.push(current);
			}
			else {
				while(!t.isEmpty()){
					int tCurrent = t.pop();
					if(current < tCurrent){
						s.push(tCurrent);
					}
					else
					{
						t.push(tCurrent);
						t.push(current);
						break;
					}
				}
				if(t.isEmpty())
					t.push(current);
			}
		}
		
		while(!t.isEmpty()) {
			s.push(t.pop());
		}
	}
	
	public static void printStackOnce(Stack<Integer> s) {
		if (s.isEmpty())
			System.out.print("<Empty>");
		
		System.out.println();
		Stack<Integer> w = (Stack<Integer>)s.clone();
		
		while(!w.isEmpty()){
			System.out.print(w.pop() + " ");
		}
	}
}
