package com.example.Emailserver.library;

import java.io.Serializable;

public class singlyLinkedList implements ILinkedList,Serializable {
	private class Node {
		public Object value;
		public Node next=null;
		public Node(Object value){
			this.value=value;
		}
	}
	public node head=null;
	public int size =0;

	public singlyLinkedList() {
		
	}

	public void printLinkekList() {
		node i=head;
		if(i==null) {
			System.out.println("Empty linked List");
		}
		else {
		while(i!=null) {
			System.out.print(i.data);
			i=i.next;
		}
		System.out.println();
	  }
	}

	@Override
	public void add(int index, Object element) {
		if(index<0) {
			System.out.println("Not Valid");
			return;
		}
		node newNode =new node(element);
		node i=head;
		node detect=head;
		if(size==0&&index!=0) {
			System.out.println("Not Valid");
			return ;
		}
		else  if(index==0) {
				newNode.next=head;
				head=newNode;
				size++;
			}
		else if(size>index) {
		
		for(int counter =1; counter<index;counter++) {
			i=i.next;
		}
		newNode.next=i.next;
		i.next=newNode;
		size++;
		}
		else if(size==index)add(element);
		else {
			System.out.println("Not Valid");
			return;
		}
	}

	@Override
	public void add(Object element) {
		node newNode =new node(element);
		node i=head;
		node detect=head;
		if(size==0) {
			newNode.next=head;
			head=newNode;
			size++;
		}
		else {
		for(int counter =1; counter<size;counter++) {
			i=i.next;
		}
		newNode.next=i.next;
		i.next=newNode;
		size++;
		}
	}

	@Override
	public Object get(int index) {
		node detect=head;
		node i=head;
		
		if(size==0) {
			return null;
		}
		else if(index<0) {
			System.out.println("not avalid");
			return null;
		}
		else if(index>=size) {
			System.out.println("not avalid");
			return null;
		}
		else {
			for(int counter =0; counter<index;counter++) {
				i=i.next;
			}
			return i.data;
		}
	}

	@Override
	public void set(int index, Object element) {
		node detect=head;
		node i=head;
		
		if(size==0) {
			System.out.println("not avalid");
			return;
		}
		else if(index<0) {
			System.out.println("not avalid");
			return;
		}
		else if(index>=size) {
			System.out.println("not avalid");
			return ;
		}
		else {
			for(int counter =0; counter<index;counter++) {
				i=i.next;
			}
			i.data=element;
			return;
		}
	}

	@Override
	public void clear() {
		 head=null;
		 size=0;
	}

	@Override
	public boolean isEmpty() {
		if(head==null) {
		return true;
		}
		else {
			return false;
		}	
	}

	@Override
	public void remove(int index) {
		node detect=head;
		node i=head;
		node j;	
		if(size==0) {
			System.out.println("not avalid");
			return ;
		}
		else if(index<0) {
			System.out.println("not avalid");
			return ;
		}
		else if(index==0) {
			head=head.next;
			size--;
		}
		else if(index>=size) {
			System.out.println("not avalid");
			return ;
		}
		else {
			for(int counter =1; counter<index;counter++) {
				i=i.next;
			}
			j=i.next;
			i.next=j.next;
			size--;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		singlyLinkedList sub =new singlyLinkedList();
		node indicator=head;
		int i=0;
		if(fromIndex<0||toIndex<0) {
			return null;
		}
		
		if(size==0)return null;
		if(fromIndex>=size||toIndex>=size) return null;
		for(int counter =0; counter<=toIndex;counter++) {
			if(counter>=fromIndex&&counter<=toIndex)
				sub.add(indicator.data);
			indicator=indicator.next;
			i++;
		}
		
		if(i!=0) {
			return sub;
		}else {
			return null;
		}
		
	}

	@Override
	public boolean contains(Object o) {
		node detect=head;
		detect=head;
		for(int counter =0; counter<size;counter++) {
			if(detect.data==o) {
				return true;
			}
			detect=detect.next;
		}
		return false;
	}
	

}