package edu.nikita.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StringUtlis {
	public static Boolean IsUniqueChars(String str){
		if(str.length() > Math.pow(2, 16))
			return false;

		HashSet<Character> set = new HashSet<Character>();
		int passed = 0;
		for(char c : str.toCharArray()){
			 set.add(c);
			 passed++;
			 if(passed != set.size())
				 return false;
		}
		return true;
	}
	
	public static char[] reverse(String str){
		char[] arr = str.toCharArray();
		int i = 0;
		int j = arr.length - 1;
		while(i < j){
			swap(arr, i, j);
			i++;
			j--;
		}
		return arr;
	}
	
	public static void swap(char[] a, int i, int j){
		char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	public static boolean areAnagrams(String a, String b){
		if(a.length() != b.length())
			return false;
		
		HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
		
		for(Character c : a.toCharArray()){
			if(freq.containsKey(c)){
				Integer i = freq.get(c);
				freq.put(c, i + 1);
				System.out.println(freq.get(c));
			}
			else
				freq.put(c, 1);
		}
		
		for(Character c : b.toCharArray()){
			if(freq.containsKey(c)){
				Integer i = freq.get(c) - 1;
				if(i == 0)
					freq.remove(c);
				else
					freq.put(c, i);
			}
			else
			 	return false;
		}
		
		return freq.size() == 0;
	}
	
	public static String replaceSpace(String a){
		StringBuffer buffer = new StringBuffer();
		for(Character c : a.toCharArray()){
			if(c == ' ')
				buffer.append("%20");
			else 
				buffer.append(c);
		}
		return buffer.toString();
	}
	
	public static String rotate(char[] a, int size){
		System.out.println();
		System.out.println();
		
		// layers
		for(int i = 0; i < size / 2; i++){
			int n = (size - i) - 1;
			// number of jumps on a layer
			for(int j = 0; j < size - i; j++)
			{
				// jumps
				char tmp = a[i + j];
				int tmpTo = (int) (i + j + Math.pow(n, 0) * (0 - 1) / 2);
				for (int r = 1; r < 4; r++)
				{	
					int from = (int) (i + j + Math.pow(n, r - 1) * (r -1) / 2);
					int to = (int) (i + j + Math.pow(n, r) * (r - 1) / 2);
					System.out.println(from + " -> " + to);
					char tmp2 = a[to];
					a[to] = tmp;
					tmp = tmp2;
				}
				a[i + j] = tmp;
			}
		}
		return new String(a);
	}
	
	public static boolean isWordRotation(String s1, String s2){
		if(s1.length() != s2.length())
			return false;
		
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		int j = 0;
		for(int i = 0; i < arr2.length; i++){
			if(arr2[i] == arr1[j]){
				j++;
			}
		}
		return s2.contains(s1.substring(j + 1));
	}
	
	public static void isWordRotation(int[][] mx){
		if(mx.length == 0)
			return;
		
		ArrayList<Integer> col = new ArrayList<Integer>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		for(int i = 0; i < mx.length; i++){
			for(int j = 0; j < mx.length; j++){
				if(mx[i][j] == 0 && !col.contains(i) && !row.contains(j))
				{
					col.add(i);
					row.add(j);
				}
			}
		}
		
		for(int i : col){
			for(int j = 0; j < mx[i].length; j++){
				mx[i][j] = 0;
			}
		}
		
		for(int i : row){
			for(int j = 0; j < mx.length; j++){
				mx[j][i] = 0;
			}
		}
	}
	
}
