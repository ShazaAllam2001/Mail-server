package com.example.Emailserver.library;

import java.io.Serializable;

public class PriorityQueueImplementation implements Serializable {
	private int size=0;
	private class Node {
		public Object value;
		public int key;
		public Node next=null;
		public Node(Object value,int key){
			this.value=value;
			this.key=key;
		}
	}
	Node head=null;
	Node tail=null;
	
	public void insert(Object item, int key) {
		Node newNode=new Node(item,key);
		if(key<1) throw new RuntimeException("Wrong key");
		if(size==0) {
			head=newNode;
			tail=newNode;
		}else {
			if(key<head.key) {
				newNode.next=head;
				head=newNode;
			}else if(key>=tail.key) {
				tail.next=newNode;
				tail=newNode;
			}else {
				Node temp;
				temp=head;
				while((temp.next.key)<=key) {
					temp=temp.next;
				}
				newNode.next=temp.next;
				temp.next=newNode;
				
			}
		}
		size++;
	}

	
	public Object removeMin() {
		Object temp;
		if(size==0)throw new RuntimeException("Empty PriorityQueue");
		if(size==1) {
			temp=head.value;
			head=tail=null;
		}else {
			temp=head.value;
			head=head.next;
		}
		size--;
		return temp;
	}

	
	public Object min() {
		Object temp;
		if(size==0)throw new RuntimeException("Empty ");
		else {
			temp=head.value;
		}
		
		return temp;
	}

	
	public boolean isEmpty() {
		if(size==0)return true;
		else
		return false;
	}

	
	public int size() {
		return this.size;
	}

	

}
