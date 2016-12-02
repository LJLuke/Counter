package com.example.lijiang.telephoneapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lijiang on 2016/11/28.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private ArrayList<Contact> mContacts;
    private OnItemClickListener mOnItemClickListener;


    public MyAdapter(Context context,ArrayList<Contact> dates) {
        this.mContext = context;
        this.mContacts = dates;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //设置联系人姓名
        holder.mTextView.setText(mContacts.get(position).getContactName());
        //获取姓名第一个字并设置
        holder.mNameView.setText(mContacts.get(position).getContactName().subSequence(0,1));
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        TextView mNameView;

        MyViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text_view_item);
            mNameView = (TextView) view.findViewById(R.id.name_view);
        }
    }
}
