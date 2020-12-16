package com.mycompany.myapp;
import java.util.*;

public class item
{
	public String noteText;
	public Date date;
	
	public item(String noteText, Date date){
		super();
		this.noteText = noteText;
		this.date = date;
	}
	public String getNoteText(){
		return noteText;
	}
	public void setNoteText(){
		this.noteText = noteText;
	}
	public Date getDate(){
		return date;
	}
	public void setDate(){
		this.date = date;
	}
}
