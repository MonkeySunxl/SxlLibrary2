package com.sxl.sxllibrary.base.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName wmt
 * @ClassDescribe
 * @Author
 * @Date 2017/4/27 11:30
 * @Copyright 未名天
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    public static final int CONTENT=0;
    public static final int HEAD=1;
    public static final int TITLE=2;
    public static final int RECYCLER=3;
    public static final int AD=4;//广告位
    protected List<T> mData=new ArrayList<>();
    protected final Context mContext;
    protected LayoutInflater mInflater;
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;
    RecyclerViewHolder holder;
    public BaseRecyclerAdapter(Context ctx, List<T> list) {
        if (isHaveHead()==false&&isHaveFoot()==false){
            mData.addAll(list);
        }else {
            //添加头部站位
            if (isHaveHead()==true&&isHaveHeadNum()>0){//判断是否需要头不见
                for (int i=0; i <isHaveHeadNum() ; i++) {//循环添加头部个数
                    mData.add(null);//用于替换position==0
                }
                mData.addAll(list);
            }
            //添加底部站位
            if (isHaveFoot()==true&&isHaveFootNum()>0){
                for (int i = 0; i <isHaveFootNum() ; i++) {
                    mData.add(null);
                }
            }
        }
        mContext = ctx;
        mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
              holder = new RecyclerViewHolder(mContext,
                    mInflater.inflate(getItemLayoutId(viewType), parent, false));
            return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        bindData(holder, position, mData.get(position));
        if (getItemViewType(position)==CONTENT){//有head  head不可点击
            if (mClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                    }
                });
            }
            if (mLongClickListener != null) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void add(int pos, T item) {
        mData.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }
   protected boolean isHaveHead(){
       return false;
   }
    protected boolean isHaveFoot(){
        return false;
    }
    protected int isHaveHeadNum(){
        return 0;
    }
    protected int isHaveFootNum(){
        return 0;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindData(RecyclerViewHolder holder, int position, T item);

    /**
     * 返回 数据集
     * @return
     */
    public List<T>  getData() {
        return mData;
    }


    public interface OnItemClickListener {
         void onItemClick(View itemView, int pos);
    }

    public interface OnItemLongClickListener {
         void onItemLongClick(View itemView, int pos);
    }
}
