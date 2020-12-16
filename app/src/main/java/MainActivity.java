package com.mycompany.myapp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.text.*;
import android.inputmethodservice.*;
import android.view.inputmethod.*;
import android.widget.AdapterView.*;

public class MainActivity extends Activity 
{
	EditText userEditText;
	Button addButton;
	ListView listView;
	ArrayList<item> arrayList;
	BaseAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		initializeAll();
		
    }
	
	public void initializeAll(){
		userEditText=(EditText) findViewById(R.id.userEditTextID);
		addButton=(Button) findViewById(R.id.addButtonID);
		listView=(ListView) findViewById(R.id.listViewID);
		
		arrayList = new ArrayList<item>();
		adapter = new BaseAdapter(){
			
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			@Override
			public int getCount()
			{
				// TODO: Implement this method
				return arrayList.size();
			}

			@Override
			public Object getItem(int position)
			{
				
				return arrayList.get(position);
			}

			@Override
			public long getItemId(int position)
			{
				// TODO: Implement this method
				return 0;
			}

			@Override
			public View getView(int position, View view, ViewGroup viewGroup)
			{
				if(view==null){
					view=inflater.inflate(R.layout.every_note_item, null);
				}
				TextView finalNoteText = (TextView) view.findViewById(R.id.noteTextItemID);
				TextView finalNoteDate = (TextView) view.findViewById(R.id.noteDateID);
				
				finalNoteText.setText(arrayList.get(position).getNoteText());
				
				finalNoteDate.setText(arrayList.get(position).getDate().toString());
				
				return view;
			}
		};
		
		listView.setAdapter(adapter);
		
		addButton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View view)
				{
					String string = userEditText.getText().toString();
					Date date = new Date();
					item itemObj = new item(string, date);
					arrayList.add(itemObj);
					
					adapter.notifyDataSetChanged();
					userEditText.setText("");
					
					InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					inputMethodManager.hideSoftInputFromWindow(userEditText.getWindowToken(), 0);
					
					Toast.makeText(MainActivity.this, "New Note Added", Toast.LENGTH_SHORT).show();
				}
		});
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View view, int position, long id)
				{
					Intent intent = new Intent(MainActivity.this, fullNote.class);
					
					String fullText = arrayList.get(position).getNoteText();
					
					intent.putExtra("FullNote", fullText);
					startActivity(intent);
				}
				
			
		});
		
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

				@Override
				public boolean onItemLongClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					PopupMenu popup = new PopupMenu(MainActivity.this, listView);
					popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

					popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							public boolean onMenuItemClick(MenuItem item) {
								Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
								return true;
							}
						});

					popup.show();
					return true;
				}
				
			
		});
	}
}
