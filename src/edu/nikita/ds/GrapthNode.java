package edu.nikita.ds;

import java.util.*;

public class GrapthNode {
	
	enum State {
		Visited,
		Visiting,
		Unvisited
	}
	
	private State state = State.Unvisited;
	
	int value;
	
	ArrayList<GrapthNode> children = new ArrayList<GrapthNode>();
	
	public GrapthNode(int val) {
		value = val;
	}
	
	public GrapthNode searchInDepth(int val) { 
		if(value == val)
			return this;
		for(GrapthNode gn : children)
		{
			GrapthNode result = gn.searchInDepth(val);
			if (result != null)
				return result;
		}
		return null;
	}
	
	public GrapthNode searchInBreadth(int val) { 
		if(value == val)
			return this;
		
		for(GrapthNode gn : children)
		{
			if (gn.value == val)
				return gn;
			GrapthNode result = gn.searchInBreadth(val);
			if (result != null)
				return result;
		}
		
		return null;
	}
	
	public boolean hasPathTo(GrapthNode target){
		Stack<GrapthNode> stack = new Stack<GrapthNode>();		
		stack.push(this);
		
		while(!stack.isEmpty()) {
			GrapthNode current = stack.pop();
			current.state = State.Visited;
			for(GrapthNode ch : children)
			{
				if(ch.state == state.Unvisited){
					if(target.equals(ch))
						return true;
					else
					{
						stack.push(ch);
					}
				}
			}
		}
		
		return false;
	}
}
