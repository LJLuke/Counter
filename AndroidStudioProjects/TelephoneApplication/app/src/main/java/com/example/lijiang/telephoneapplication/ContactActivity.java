package com.example.lijiang.telephoneapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactActivity extends AppCompatActivity {
    private TextView mPhoneNumberView;
    private TextView mContactName;
    private ImageView mNoteView;
    private int mPosition;
    private ArrayList<Contact> mTelephoneContacts = new ArrayList<>();
    private Toolbar mToolbar;
    private Button mDeleteContactButton;
    private Button mEditContactButton;
    private Button mEnsureButton;
    private Button mCancleButton;
    private EditText mRenameContact;
    private EditText mResetNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mPhoneNumberView = (TextView) findViewById(R.id.phone_number_view);
        //接收MainActivity传递的position
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mPosition = bundle.getInt("index");
        mTelephoneContacts = MainActivity.mContacts;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactActivity.this.finish();
            }
        });
        //设置联系人电话
        mPhoneNumberView.setText(mTelephoneContacts.get(mPosition).getContactNumber());
        //点击后跳转拨号界面
        mPhoneNumberView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:" + mTelephoneContacts.get(mPosition).getContactNumber()));
                startActivity(intent1);
            }
        });
        mNoteView = (ImageView) findViewById(R.id.note_view);
        //点击后跳转写短信界面
        mNoteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setAction(intent2.ACTION_SENDTO);
                intent2.setData(Uri.parse("smsto:" + mTelephoneContacts.get(mPosition).getContactNumber()));
                startActivity(intent2);
            }
        });
        mContactName = (TextView) findViewById(R.id.contact_name);
        mContactName.setText(mTelephoneContacts.get(mPosition).getContactName());
        //删除联系人
        mDeleteContactButton = (Button) findViewById(R.id.delete_contact);
        mDeleteContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder
                        (ContactActivity.this);
                dialog.setTitle("删除");
                dialog.setMessage("你确定要删除此联系人吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTelephoneContacts.remove(mPosition);
                        MainActivity.mAdapter.notifyItemRemoved(mPosition);
                        MainActivity.mAdapter.notifyItemRangeChanged(mPosition,mTelephoneContacts.size());
                        ContactActivity.this.finish();
                        Toast.makeText(ContactActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        //自定义一个dialog编辑联系人（有点鸡肋）
        mEditContactButton = (Button) findViewById(R.id.edit_contact);
        mEditContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(ContactActivity.this)
                        .create();
                builder.show();
                builder.getWindow().setContentView(R.layout.dialog_view);
                builder.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                 mEnsureButton = (Button) builder.findViewById(R.id.ensure_button);
                mRenameContact = (EditText) builder.findViewById(R.id.rename_contact);
                mResetNumber = (EditText) builder.findViewById(R.id.reset_number);
                mEnsureButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String rename = mRenameContact.getText().toString();
                        String number = mResetNumber.getText().toString();
                        mTelephoneContacts.get(mPosition).setContactName(rename);
                        mTelephoneContacts.get(mPosition).setContactNumber(number);
                        builder.dismiss();
                        MainActivity.mAdapter.notifyItemChanged(mPosition);
                        Toast.makeText(ContactActivity.this,"编辑成功",Toast.LENGTH_SHORT).show();
                    }
                });
                mCancleButton = (Button) builder.findViewById(R.id.cancel_button);
                mCancleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                });
            }
        });
    }
}

