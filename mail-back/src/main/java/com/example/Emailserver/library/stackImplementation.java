package com.example.Emailserver.library;

import java.io.Serializable;

public class stackImplementation implements Serializable{

	private class Node {
		public Object value;
		public Node next=null;
		public Node(Object value){
			this.value=value;
		}
	}
	public Node head=null;
	private int size=0;
	
	public Object pop() {
		Object temp;
		if(head==null) {
			throw new RuntimeException("Empty Stack");
		}else {
			temp=head.value;
			head=head.next;
			size--;
		}
		return temp;
	}

	public Object peek() {
		Object temp;
		if(head==null) {
			throw new RuntimeException("Empty Stack");
		}else {
			 temp=head.value;
			
		}
		return temp;
	}

	public void push(Object element) {
		Node newNode=new Node(element);
		if(head==null) {
			newNode.next=null;
			head=newNode;
			size++;
		}else {
			newNode.next=head;
			head=newNode;
			size++;
			
		}
		
	}

	public boolean isEmpty() {
		if(head==null) return true;
		return false;
	}

	public int size() {
		return size;
	}
	
}
