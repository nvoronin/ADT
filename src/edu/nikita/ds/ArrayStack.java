package edu.nikita.ds;

public class ArrayStack {
	
	private int[] a = new int[10];
	
	public ArrayStack(){
		
	}
	
	public void push(int val, int stack){
		for(int i = 0; i < a.length / 3; i++) {
			int index = getAddr(i, stack);
			if(a[index] == 0) {
				a[index] = val;
				return;
			} 
		}
	}
	
	public int pop(int stack){
		for(int i = 0; i < a.length / 3; i++) {
			int index = getAddr(i, stack);
			if(a[index] == 0) {
				index = getAddr(i - 1, stack);
				if(index >= 0){
					int val = a[index];
					a[index] = 0;
					return val;
				}
				else
					return 0;
			} 
		}
		return 0;
	}
	
	private int getAddr(int i, int stack){
		return i * 3 + stack;
	}
	
}
