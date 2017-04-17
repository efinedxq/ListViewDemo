package com.example.work_5_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private List<User> list = new ArrayList<User>();
	private List<String> fav;
	private String sex;

	// 定义ListView对象变量---View
	private ListView listview;

	// 存放数据的List<String>对象---Model
	private List<Map<String, Object>> showlist;

	private Button btAdd;
	private Button btDelete;
	private Button btModify;
	private Button btQuery;

	private EditText etName;
	private EditText etAge;

	private RadioGroup rgSex;
	private RadioButton rbMale;
	private RadioButton rbFemale;

	private CheckBox[] cbFav = new CheckBox[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listview = (ListView) findViewById(R.id.work_5);
		showlist = new ArrayList<Map<String, Object>>();

		etName = (EditText) findViewById(R.id.name);
		etAge = (EditText) findViewById(R.id.age);

		rgSex = (RadioGroup) findViewById(R.id.sex);
		rbMale = (RadioButton) findViewById(R.id.male);
		rbFemale = (RadioButton) findViewById(R.id.female);

		cbFav[0] = (CheckBox) findViewById(R.id.fav1);
		cbFav[1] = (CheckBox) findViewById(R.id.fav2);
		cbFav[2] = (CheckBox) findViewById(R.id.fav3);

		btAdd = (Button) findViewById(R.id.add);
		btDelete = (Button) findViewById(R.id.delete);
		btModify = (Button) findViewById(R.id.modify);
		btQuery = (Button) findViewById(R.id.query);

		btAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addUser();
				showAll();
			}
		});

		btDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteUser();
				showAll();
			}
		});
		btModify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (deleteUser()) {
					addUser();
				}
				showAll();
			}
		});
		btQuery.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showOne();
			}
		});
	}

	private void showOne() {
		User user = new User();
		showlist.clear();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(etName.getText().toString())) {
				user = list.get(i);
				Map<String, Object> map;

				map = new HashMap<String, Object>();
				map.put("name", user.getName());
				map.put("sex", user.getSex());
				map.put("age", user.getAge());

				StringBuffer str = new StringBuffer();
				for (String s : user.getFav()) {
					str.append(s + " ");
				}
				map.put("sfav", str);
				showlist.add(map);
			}
		}
		// 创建Adapter对象
		SimpleAdapter adapter = new SimpleAdapter(this, // 上下文对象
				showlist, // 存放数据的List对象
				R.layout.item, // 每行的布局
				new String[] { "name", "sex", "age", "sfav" }, // 数据对象Map中的列名--键
				new int[] { R.id.name, R.id.sex, R.id.age, R.id.favs }); // 列内容--Listview中的控件ID，对应控件用于显示Map对象中的值

		listview.setAdapter(adapter);

	}

	private boolean deleteUser() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(etName.getText().toString())) {

				list.remove(i);
				return true;
			}
		}
		return false;
	}

	private void addUser() {
		User user = new User();
		fav = new ArrayList<String>();
		if (rgSex.getCheckedRadioButtonId() == R.id.female) {
			sex = "女";
		} else {
			sex = "男";
		}
		for (int i = 0; i < 3; i++) {
			if (cbFav[i].isChecked()) {
				fav.add(cbFav[i].getText().toString());
			}
		}
		user.setName(etName.getText().toString());
		user.setAge(etAge.getText().toString());
		user.setSex(sex);
		user.setFav(fav);
		list.add(user);
	}

	private void showAll() {
		User user = new User();
		showlist.clear();
		for (int i = 0; i < list.size(); i++) {
			user = list.get(i);
			Map<String, Object> map;

			map = new HashMap<String, Object>();
			map.put("name", user.getName());
			map.put("sex", user.getSex());
			map.put("age", user.getAge());

			StringBuffer str = new StringBuffer();
			for (String s : user.getFav()) {
				str.append(s + " ");
			}
			map.put("sfav", str);
			showlist.add(map);
		}
		// 创建Adapter对象
		SimpleAdapter adapter = new SimpleAdapter(this, // 上下文对象
				showlist, // 存放数据的List对象
				R.layout.item, // 每行的布局
				new String[] { "name", "sex", "age", "sfav" }, // 数据对象Map中的列名--键
				new int[] { R.id.name, R.id.sex, R.id.age, R.id.favs }); // 列内容--Listview中的控件ID，对应控件用于显示Map对象中的值

		listview.setAdapter(adapter);
	}
}
