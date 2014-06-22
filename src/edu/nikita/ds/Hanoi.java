package edu.nikita.ds;

import java.util.*;

public class Hanoi {
	private ArrayList<Stack<Integer>> s = new ArrayList<Stack<Integer>>();
	
	public Hanoi(){
		s.add(new Stack<Integer>());
		s.add(new Stack<Integer>());
		s.add(new Stack<Integer>());
		
		Stack<Integer> s1 = s.get(0);
		
		s1.push(4);
		s1.push(3);
		s1.push(2);
		s1.push(1);
	}
	
	public void GoMagic(){
		int iter = 0;
		int last = 1;
		boolean totheLeft = false;
		
		System.out.println("____");
		print();
		
		System.out.println("____");
		print();
		
		int it = 1;
		boolean b= true;
		while(b) {
			// moving right
			while(canMove(s.get(0), s.get(1)) || canMove(s.get(1), s.get(2)))
			{
				if(canMove(s.get(0), s.get(1)) && (totheLeft || (!totheLeft && last != 0))){
				   move(s.get(0), s.get(1));
				   totheLeft = true;
				   last = 1;
				}
				
				if(canMove(s.get(1), s.get(2)) && (totheLeft || (!totheLeft && last != 1))){
				   move(s.get(1), s.get(2));
				   totheLeft = true;
				   last = 2;
				}
			}
			
			System.out.println("->");
			print();
			
			// moving left
			while(canMove(s.get(1), s.get(0)) || canMove(s.get(2), s.get(1)))
			{
				if(canMove(s.get(1), s.get(0)) && (!totheLeft || (totheLeft && last != 1))){
				   move(s.get(1), s.get(0));
				   totheLeft = false;
				   last = 0;
				}
					
				if(canMove(s.get(2), s.get(1)) && (!totheLeft || (totheLeft && last != 2))){
				   move(s.get(2), s.get(1));
				   totheLeft = false;
				   last = 1;
				}
			}
			
			System.out.println("<-");
			print();
			
			it++;
			
			b = !(s.get(0).isEmpty() && s.get(1).isEmpty());
		}
		
		System.out.println("Iter = " + it);
	}
	
	private boolean canMove(Stack<Integer> from, Stack<Integer> to){
		return !from.isEmpty() && (to.isEmpty() || (to.peek() > from.peek()));
	}
	
	private void move(Stack<Integer> from, Stack<Integer> to){
		if(canMove(from, to))
			to.push(from.pop());
		else 
			throw new IndexOutOfBoundsException();
	}
	
	private void print(){
		ArrayList<Stack<Integer>> newS = new ArrayList<Stack<Integer>>();
		for(Stack<Integer> st : s){
			Stack<Integer> v = (Stack<Integer>)st.clone();
			newS.add(v);
			if(st.isEmpty())
				System.out.print("_");
			while(!st.isEmpty()){
				int i = st.pop();
				System.out.print(i + " ");
			}
			System.out.println();
		}
		s = newS;
		System.out.println();
	}
}
