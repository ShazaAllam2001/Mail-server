package com.example.Emailserver.library;

import java.io.Serializable;

public class queueLinkedListImplementation implements Serializable{
	private int counter=0;
	private class Node {
		public Object value;
		public Node next=null;
		public Node(Object value){
			this.value=value;
		}
	}
	Node front=null;
	Node rear=null;

	public void enqueue(Object item) {
		// TODO Auto-generated method stub
		Node newNode= new Node(item);	
		newNode.next=null;
		if(isEmpty()) {
			front=rear=newNode;
		}else {
			rear.next=newNode;
			rear=newNode;
		}
		counter++;
		
	}

	public Object dequeue() {
			Object temp;
		if(isEmpty())throw new RuntimeException("Empty queue");
		if(front==rear) {
			temp=front.value;
			front=rear=null;
		}
		else {
			temp=front.value;
			front=front.next;
		}
		counter--;
		return temp;
	}
	public boolean isEmpty() {
		if(front==null) return true;
		else
		return false;
	}

	public int size() {
		return this.counter;
	}

	

}
