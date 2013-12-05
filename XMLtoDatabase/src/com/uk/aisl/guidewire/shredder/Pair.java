package com.uk.aisl.guidewire.shredder;

public class Pair<T, U> {
	
	private T first;
	private U second;
	
	public Pair(){}
	
	public Pair(T first, U second){
		this.first = first;
		this.second = second;
	}
	
	public T getLeft(){
		return first;
	}
	
	public void setLeft(T first){
		this.first = first;
	}
	
	public U getRight(){
		return second;
	}
	
	public void setRight(U second){
		this.second = second;
	}

}
