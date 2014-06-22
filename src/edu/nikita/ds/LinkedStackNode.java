package edu.nikita.ds;

public class LinkedStackNode {
	private int min = 0;
	
	private int value;
	
	private LinkedStackNode next;
	
	public LinkedStackNode(int val) {
		value = val;
		min = val;
	}
	
	private LinkedStackNode(int val, LinkedStackNode tail) {
		this(val);
		next = tail;
	}
	
	private LinkedStackNode(int val, LinkedStackNode tail, int min) {
		this(val, tail);
		this.min = min;
	}
	
	public LinkedStackNode push(int val) {
		return new LinkedStackNode(val, this, min > val ? val : min);
	}
	
	public int pop(){
		int r = value;
		if(next != null){
			value = next.value;
			next = next.next;
			min = next.min;
		}
		return r;
	}
	
	public boolean isEmpty(){
		return next == null;
	}
	
	public int getMin(){
		return min;
	}
}
