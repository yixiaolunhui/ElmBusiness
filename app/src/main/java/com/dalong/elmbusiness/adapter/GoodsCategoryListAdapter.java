package com.dalong.elmbusiness.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dalong.elmbusiness.entity.GoodsListBean;
import com.dalong.elmbusiness.R;

import java.util.List;

/**
 * 商品分类adapter
 * Created by dalong on 2016/12/27.
 */
public class GoodsCategoryListAdapter extends MyBaseAdapter<GoodsListBean.DataEntity.GoodscatrgoryEntity> {

    //当前选中的位置
    private int selectPosition;

    public GoodsCategoryListAdapter(List<GoodsListBean.DataEntity.GoodscatrgoryEntity> dataList, Context context) {
        super(dataList, context);
    }

    public  void  changeData(List<GoodsListBean.DataEntity.GoodscatrgoryEntity> dataList){
        this.dataList=dataList;
        notifyDataSetChanged();
    }

    /**
     * 设置选中index
     * @param position
     */
    public void setCheckPosition(int position) {
        this.selectPosition = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_goods_category_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.goodsCategoryName.setText(dataList.get(position).getName());
        holder.shopCartNum.setText(String.valueOf(dataList.get(position).getBugNum()));
        if(dataList.get(position).getBugNum()>0){
            holder.shopCartNum.setVisibility(View.VISIBLE);
        }else{
            holder.shopCartNum.setVisibility(View.GONE);
        }

        if (selectPosition != -1) {
            if (selectPosition == position) {
                convertView.setBackgroundResource(R.drawable.goods_category_list_bg_select);
            } else {
                convertView.setBackgroundResource(R.drawable.goods_category_list_bg_normal);
            }
        } else {
            convertView.setBackgroundResource(R.drawable.goods_category_list_bg_normal);
        }
        return convertView;
    }

    public class ViewHolder {
        public final TextView goodsCategoryName;
        public final TextView shopCartNum;
        public final View root;

        public ViewHolder(View root) {
            goodsCategoryName = (TextView) root.findViewById(R.id.goodsCategoryName);
            shopCartNum = (TextView) root.findViewById(R.id.shopCartNum);
            this.root = root;
        }
    }
}
