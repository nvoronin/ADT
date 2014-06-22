package edu.nikita.ds;

import java.util.*;

public class CustomComparer implements Comparator<Character> {

	private String str;
	
	public CustomComparer(String str) {
		this.str = str;
	}
	
	@Override
	public int compare(Character o1, Character o2) {
		// TODO Auto-generated method stub
		return  ((Integer)str.indexOf(o2)).compareTo(str.indexOf(o1));
	}
	
}
