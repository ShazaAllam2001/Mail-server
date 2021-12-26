package com.example.Emailserver.library;

import java.io.Serializable;

public class dnode implements Serializable{
	public Object data;
	public dnode next=null;
	public dnode previous=null;
	public dnode(Object data) {
		this.data=data;
		
	}
}
