package com.xjl.effectivenavigation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	public DrawerLayout drawerLayout;// ���������
	public ListView leftList;// ������ڵ�ѡ��
	public ArrayAdapter<String> arrayAdapter;
	private String[] items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}

	// ��ʼ���ؼ�
	private void initViews() {
		drawerLayout = (DrawerLayout) findViewById(R.id.main_layout);
		items = getResources().getStringArray(R.array.left_array);
		leftList = (ListView) findViewById(R.id.left_drawer);
		arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
		leftList.setAdapter(arrayAdapter);
		leftList.setOnItemClickListener(itemListener);
		initFragments();

	}

	// �����Ƭ
	private void initFragments() {
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		DrawerFragment fragment = new DrawerFragment();
		// ������fragment�а�ť�����Ʋ�����Ĵ�
		fragment.setDrawerLayout(drawerLayout, leftList);
		transaction.add(R.id.main_content, fragment);
		transaction.commit();
	}

	// ѡ�����¼�
	OnItemClickListener itemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			// TODO Auto-generated method stub
			// ����Activity�ı��⣬����ֻ��������һ�����ԣ�����������������������������ѡ���¼�
			setTitle(items[position]);
			// �رղ����
			drawerLayout.closeDrawer(leftList);
			Log.i("onItemSelected",
					"open?:" + drawerLayout.isDrawerOpen(leftList));
		}

	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int,
	 * android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// ʹ��menu���򿪻�رղ����
		if (keyCode == KeyEvent.KEYCODE_MENU) {

			if (drawerLayout.isDrawerOpen(leftList)) {
				drawerLayout.closeDrawer(leftList);
			} else {
				drawerLayout.openDrawer(leftList);
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
