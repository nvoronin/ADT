package edu.nikita.ds;

import java.util.HashSet;

public class LinkedListNode<T> {
	
	private LinkedListNode<T> next = null;
	
	private T value = null;
	
	public LinkedListNode(T value){
		this.value = value;
	}
	
	public LinkedListNode(T value, LinkedListNode<T> next){
		this(value);
		this.setNext(next); 
	}
	
	public boolean isTail(){
		return getNext() == null;
	}
	
	public T getValue() {
		return value;
	}
	
	public LinkedListNode<T> appendToHead(T val){
		return new LinkedListNode<T>(val, this);
	}
	
	public LinkedListNode<T> appendToTail(T val){
		if (isTail())
		{
			setNext(new LinkedListNode<T>(val));
			return getNext();
		}
		else
			return getNext().appendToTail(val);
	}

	public LinkedListNode<T> getNext() {
		return next;
	}

	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}
	
	public LinkedListNode<T> delete(T val) {
		if(value.equals(val))
			return next;
		
		LinkedListNode<T> prev = this;
		LinkedListNode<T> n = next;
		while(n != null) {
			if(n.value.equals(val))
			{
				prev.setNext(n.next);
				return this;
			}
			prev = n;
			n = n.next;
		}
		return this;
	}
	
	public void removeDuplicates(){
		HashSet<T> set = new HashSet<T>();
		LinkedListNode<T> prev = null;
		LinkedListNode<T> n = this;
		while(n != null){
			int size = set.size();
			set.add(n.value);
			if(size == set.size()){
				prev.next = n.next;
				n = prev.next;
			}
			else {
				prev = n;
				n = n.next;
			}
		}
	}
	
	public void removeDuplicatesInPlace(){
		LinkedListNode<T> list = null;
		LinkedListNode<T> listToCheck = this;
		while(listToCheck != null){
			T val = listToCheck.value;
			list = listToCheck.next;
			LinkedListNode<T> prev = listToCheck;
			while(list != null){
				if(list.value == val)
				{
					prev.next = list.next;
					list = list.next;
				}
				else{
					prev = list;
					list = list.next;
				}
			}
			listToCheck = listToCheck.next;
		}
	}
	
	public LinkedListNode<T> nth(int indexFromTheEnd){
		LinkedListNode<T> result = this;
		LinkedListNode<T> n = this;
		int index = 0;
		while(n != null){
			if(index >= indexFromTheEnd + 1){
				result = result.next;
			}
			index++;
			n = n.next;
		}
		
		if(index < indexFromTheEnd)
			return null;
		return result;
	}
	
	public void deleteMe(){
		value = next.value;
		next = next.next;
	}
	
	public static LinkedListNode<Integer> sum(LinkedListNode<Integer> n1, LinkedListNode<Integer> n2){
		
		LinkedListNode<Integer> head = null;
		LinkedListNode<Integer> prev = null;
		LinkedListNode<Integer> node = null;
		
		boolean addNext = false;
		while(n1 != null || n2 != null) {
			
			if(n1 != null && n2 != null){
				int s = (n1.value + n2.value) + (addNext ? 1 : 0); 
				node = new LinkedListNode<Integer>(s % 10);
				addNext = s >= 10;
			}
			else if(n1 != null || n2 != null) {
				int s = n1 == null ? n2.value : n1.value;
				s = s + (addNext ? 1 : 0);
				node = new LinkedListNode<Integer>(s % 10);
				addNext = s >= 10;
			}
			
			if(n1 != null)
				n1 = n1.next;
			if(n2 != null)
				n2 = n2.next;
			  
			if(head == null){
				head = node;
			}
			else
			{
				prev.next = node;
			}
			prev = node;
		}
		
		if(addNext){
			node.appendToTail(1);
		}
		
		return head;
	}
	
	public LinkedListNode<T> findCercleByPointers(){
		LinkedListNode<T> p2 = this;
		LinkedListNode<T> p = this;
		
		int index = 0;
		do {
			p = p.next;
			p2 = p2.next.next;
		} while (p != null && p2 != null && p.value != p2.value);
		
		do {
			p = p.next;
			p2 = p2.next.next;
		} while (p != null && p2 != null && p.value != p2.value);
		
		return p;
	}
	
	public LinkedListNode<T> findCercle(){
		HashSet<LinkedListNode<T>> set = new HashSet<LinkedListNode<T>>(); 
		LinkedListNode<T> p = this;
		while (p != null) {
			int size = set.size();
			set.add(p);
			if(set.size() == size)
				return p;
			p = p.next;
		}
		return null;
	}
	
	@Override
	public String toString(){
		LinkedListNode<T> n = this;
		StringBuffer sb = new StringBuffer();
		while(n != null){
			sb.append(n.value.toString() + " -> ");
			n = n.next;
		}
		sb.append("null");
		return sb.toString();
	} 
}
