package com.dalong.elmbusiness.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dalong.elmbusiness.entity.GoodsListBean;
import com.dalong.elmbusiness.event.GoodsListEvent;
import com.dalong.elmbusiness.event.MessageEvent;
import com.dalong.elmbusiness.R;
import com.dalong.elmbusiness.ui.TabActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * 商品adapter
 * Created by dalong on 2016/12/27.
 */
public class GoodsListAdapter extends MyBaseAdapter<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity>
        implements StickyListHeadersAdapter, SectionIndexer {

    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity> goodscatrgoryEntities;
    private Activity mActivity;
    private TextView shopCart;
    private ImageView buyImg;
    private ViewGroup animMaskLayout;
    private int[] goodsNum;
    private int buyNum;
    private int totalPrice;
    private int[] mSectionIndices;
    private int[]  mGoodsCategoryBuyNums;
    private String[] mSectionLetters;

    public GoodsListAdapter(List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> dataList
            , Context ctx
            , List<GoodsListBean.DataEntity.GoodscatrgoryEntity> goodscatrgoryEntities) {
        super(dataList, ctx);
        this.goodscatrgoryEntities = goodscatrgoryEntities;
        initGoodsNum();
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
        mGoodsCategoryBuyNums = getBuyNums();
    }

    /**
     * 初始化各个商品的购买数量
     */
    private void initGoodsNum() {
        int leng = dataList.size();
        goodsNum = new int[leng];
        for (int i = 0; i < leng; i++) {
            goodsNum[i] = 0;
        }
    }

    public void setGoodscatrgoryEntities(List<GoodsListBean.DataEntity.GoodscatrgoryEntity> goodscatrgoryEntities) {
        this.goodscatrgoryEntities = goodscatrgoryEntities;
        notifyDataSetChanged();
    }

    public void setShopCart(TextView shopCart) {
        this.shopCart = shopCart;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_goods_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置名
        holder.goodsCategoryName.setText(dataList.get(position).getName());
        //设置说明
        holder.tvGoodsDescription.setText(dataList.get(position).getIntroduce());
        //设置价格
        holder.tvGoodsPrice.setText("¥"+dataList.get(position).getPrice());

        Glide
                .with(mContext)
                .load(dataList.get(position).getGoodsImgUrl())
                .centerCrop()
                .placeholder(R.mipmap.icon_logo_image_default)
                .crossFade()
                .into(holder.ivGoodsImage);

        //通过判别对应位置的数量是否大于0来显示隐藏数量
        isSelected(goodsNum[position], holder);

        //加号按钮点击
        holder.ivGoodsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsNum[position]++;
                mGoodsCategoryBuyNums[dataList.get(position).getId()]++;
                buyNum++;
                totalPrice+=dataList.get(position).getPrice();
                if (goodsNum[position]<=1) {
                    holder.ivGoodsMinus.setAnimation(getShowAnimation());
                    holder.tvGoodsSelectNum.setAnimation(getShowAnimation());
                    holder.ivGoodsMinus.setVisibility(View.VISIBLE);
                    holder.tvGoodsSelectNum.setVisibility(View.VISIBLE);
                }
                startAnim(holder.ivGoodsAdd);
                changeShopCart();
                mOnGoodsNunChangeListener.onNumChange();
                isSelected(goodsNum[position], holder);
            }
        });
        //减号点击按钮点击
        holder.ivGoodsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsNum[position] > 0) {
                    goodsNum[position]--;
                    mGoodsCategoryBuyNums[dataList.get(position).getId()]--;
                    isSelected(goodsNum[position], holder);
                    buyNum--;
                    totalPrice-=dataList.get(position).getPrice();
                    if (goodsNum[position] <=0) {
                        holder.ivGoodsMinus.setAnimation(getHiddenAnimation());
                        holder.tvGoodsSelectNum.setAnimation(getHiddenAnimation());
                        holder.ivGoodsMinus.setVisibility(View.GONE);
                        holder.tvGoodsSelectNum.setVisibility(View.GONE);
                    }
                    changeShopCart();
                    mOnGoodsNunChangeListener.onNumChange();
                } else {

                }
            }
        });
        return convertView;
    }

    /**
     * 开始动画
     * @param view
     */
    private void startAnim(View view) {
        buyImg = new ImageView(mActivity);
        buyImg.setBackgroundResource(R.mipmap.icon_goods_add_item);// 设置buyImg的图片
        int[] loc = new int[2];
        view.getLocationInWindow(loc);
        int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
        view.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
        ((TabActivity)mActivity).setAnim(buyImg, startLocation);// 开始执行动画
    }

    /**
     * 判断商品是否有添加到购物车中
     * @param i  条目下标
     * @param vh ViewHolder
     */
    private void isSelected(int i, ViewHolder vh) {
        if (i == 0) {
            vh.tvGoodsSelectNum.setVisibility(View.GONE);
            vh.ivGoodsMinus.setVisibility(View.GONE);
        } else {
            vh.tvGoodsSelectNum.setVisibility(View.VISIBLE);
            vh.tvGoodsSelectNum.setText(i + "");
            vh.ivGoodsMinus.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object[] getSections() {
        return mSectionLetters;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (mSectionIndices.length == 0) {
            return 0;
        }
        if (sectionIndex >= mSectionIndices.length) {
            sectionIndex = mSectionIndices.length - 1;
        } else if (sectionIndex < 0) {
            sectionIndex = 0;
        }
        return mSectionIndices[sectionIndex];
    }

    @Override
    public int getSectionForPosition(int position) {
        for (int i = 0; i < mSectionIndices.length; i++) {
            if (position < mSectionIndices[i]) {
                return i - 1;
            }
        }
        return mSectionIndices.length - 1;
    }

    /**
     * 存放每个组里的添加购物车的数量
     * @return
     */
    public int[] getBuyNums() {
        int[] letters = new int[goodscatrgoryEntities.size()];
        for (int i = 0; i < goodscatrgoryEntities.size(); i++) {
            letters[i] = goodscatrgoryEntities.get(i).getBugNum();
        }
        return letters;
    }
    /**
     * 存放每个分组的第一条的ID
     *
     * @return
     */
    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        int lastFirstPoi = -1;
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getId() != lastFirstPoi) {
                lastFirstPoi = dataList.get(i).getId();
                sectionIndices.add(i);
            }
        }
        int[] sections = new int[sectionIndices.size()];
        for (int i = 0; i < sectionIndices.size(); i++) {
            sections[i] = sectionIndices.get(i);
        }
        return sections;
    }

    /**
     * 填充每一个分组要展现的数据
     *
     * @return
     */
    private String[] getSectionLetters() {
        String[] letters = new String[mSectionIndices.length];
        for (int i = 0; i < mSectionIndices.length; i++) {
            letters[i] = goodscatrgoryEntities.get(i).getName();
        }
        return letters;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder hVH;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.header_goods_list, null);
            hVH = new HeaderViewHolder(convertView);
            convertView.setTag(hVH);
        } else {
            hVH = (HeaderViewHolder) convertView.getTag();
        }
        hVH.tvGoodsItemTitle.setText(goodscatrgoryEntities.get(
                dataList.get(position).getId()).getName());
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return dataList.get(position).getId();
    }

    public void clear() {
        mSectionIndices = new int[0];
        mSectionLetters = new String[0];
        notifyDataSetChanged();
    }

    public void restore() {
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
        notifyDataSetChanged();
    }


    /**
     * 显示减号的动画
     * @return
     */
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }


    /**
     * 隐藏减号的动画
     * @return
     */
    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,4f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }


    /**
     * 修改购物车状态
     */
    private void changeShopCart() {
        EventBus.getDefault().post(new MessageEvent(buyNum,totalPrice));
        EventBus.getDefault().post(new GoodsListEvent(mGoodsCategoryBuyNums));
        if(shopCart==null)return;
        if (buyNum > 0) {
            shopCart.setVisibility(View.VISIBLE);
            shopCart.setText(buyNum + "");
        } else {
            shopCart.setVisibility(View.GONE);
        }

    }




    public interface OnShopCartGoodsChangeListener {
        public void onNumChange();
    }

    private OnShopCartGoodsChangeListener mOnGoodsNunChangeListener = null;

    public void setOnShopCartGoodsChangeListener(OnShopCartGoodsChangeListener e){
        mOnGoodsNunChangeListener = e;
    }

    private class HeaderViewHolder {
        public final TextView tvGoodsItemTitle;
        public final View root;

        public HeaderViewHolder(View root) {
            tvGoodsItemTitle = (TextView) root.findViewById(R.id.tvGoodsItemTitle);
            this.root = root;
        }
    }

    public class ViewHolder {
        public final ImageView ivGoodsImage;
        public final TextView goodsCategoryName;
        public final TextView tvGoodsDescription;
        public final LinearLayout goodsInfo;
        public final TextView tvGoodsPrice;
        public final TextView tvGoodsIntegral;
        public final ImageView ivGoodsMinus;
        public final TextView tvGoodsSelectNum;
        public final ImageView ivGoodsAdd;
        public final View root;

        public ViewHolder(View root) {
            ivGoodsImage = (ImageView) root.findViewById(R.id.ivGoodsImage);
            goodsCategoryName = (TextView) root.findViewById(R.id.goodsCategoryName);
            tvGoodsDescription = (TextView) root.findViewById(R.id.tvGoodsDescription);
            goodsInfo = (LinearLayout) root.findViewById(R.id.goodsInfo);
            tvGoodsPrice = (TextView) root.findViewById(R.id.tvGoodsPrice);
            tvGoodsIntegral = (TextView) root.findViewById(R.id.tvGoodsIntegral);
            ivGoodsMinus = (ImageView) root.findViewById(R.id.ivGoodsMinus);
            tvGoodsSelectNum = (TextView) root.findViewById(R.id.tvGoodsSelectNum);
            ivGoodsAdd = (ImageView) root.findViewById(R.id.ivGoodsAdd);
            this.root = root;
        }
    }
}
