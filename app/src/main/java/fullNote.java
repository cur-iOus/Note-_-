package com.mycompany.myapp;
import android.app.*;
import android.os.*;
import android.widget.*;

public class fullNote extends Activity
{
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_note);
		
		textView = (TextView) findViewById(R.id.fullnoteTextViewID);
		
		textView.setText(getIntent().getStringExtra("FullNote"));
		Toast.makeText(fullNote.this, "Full View", Toast.LENGTH_LONG).show();
	}
	
}
