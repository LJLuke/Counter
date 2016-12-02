package com.example.lijiang.telephoneapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

//只实现当前APP的删除和编辑，无法改变手机联系人数据库

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    //设置为static方便联系人删除和编辑是刷新数据
    public static MyAdapter mAdapter;
    //设置为static方便删除和编辑联系人
    public static ArrayList<Contact> mContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(MainActivity.this, mContacts);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //实现跳转并将position传递给ContactActivity
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //动态获取android 6.0 手机联系人权限
        getPermission(MainActivity.this);
        //获取联系人信息
        getPhoneContacts();
    }
    private void getPermission(Context context) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    1);
        }
    }

    private void getPhoneContacts() {
        ContentResolver contentResolver = getContentResolver();
        Cursor phoneCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (phoneCursor.moveToNext()) {
            //创建一个联系人对象
            Contact mContact = new Contact();
            int nameFieldColumnIndex = phoneCursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String contactName = phoneCursor.getString(nameFieldColumnIndex);
            int numberFieldColumnIndex = phoneCursor.getColumnIndex(ContactsContract.Contacts._ID);
            String ContactID = phoneCursor.getString(numberFieldColumnIndex);
            Cursor phone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactID, null, null);
            while (phone.moveToNext()) {
                String phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //将从手机获取的联系人姓名set给联系人对象
                mContact.setContactName(contactName);
                //将从手机获取的联系人电话号码set给联系人对象
                mContact.setContactNumber(phoneNumber);
                mContacts.add(mContact);
            }
            phone.close();
        }
        phoneCursor.close();
        mAdapter.notifyDataSetChanged();
    }


}

