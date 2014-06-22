package edu.nikita.ds;

import java.util.*;

public class MyHashMap<T, U> {
	class KeyValuePair {
		public T key;
		public U value;
		
		public KeyValuePair(T key, U value){
			this.key = key;
			this.value = value;
		}
	}
	
	private ArrayList<LinkedList<KeyValuePair>> buckets = null;
	
	private int numberOfBuckets;
	
	public MyHashMap(int numberOfBuckets) {
		this.numberOfBuckets = numberOfBuckets;
		buckets = new ArrayList<LinkedList<KeyValuePair>>();
	}
	
	public MyHashMap()
	{
		this(1024);
	}
	
	public U get(T key){
		int hash = key.hashCode();
		LinkedList<KeyValuePair> list = buckets.get(hash % numberOfBuckets);
		
		if(list == null)
			return null;
		
		for(KeyValuePair pair : list){
			if(pair.key.equals(key))
				return pair.value;
		}
		return null;
	}
	
	public void add(T key, U value){
		int hash = key.hashCode();
		int index = hash % numberOfBuckets;
		LinkedList<KeyValuePair> list = buckets.get(index);
		
		if(list == null)
		{
			list = new LinkedList<MyHashMap<T,U>.KeyValuePair>();
			buckets.add(index, list);
		}
		
		for(KeyValuePair pair : list){
			if(pair.key.equals(key))
			{
				pair.value = value;
				return;
			}
		}

		list.add(new KeyValuePair(key, value));
	}
}
