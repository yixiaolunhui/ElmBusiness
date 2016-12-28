package com.dalong.elmbusiness.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dalong.elmbusiness.entity.GoodsListBean;
import com.dalong.elmbusiness.R;
import com.dalong.elmbusiness.adapter.GoodsCategoryListAdapter;
import com.dalong.elmbusiness.adapter.GoodsListAdapter;
import com.dalong.elmbusiness.event.GoodsListEvent;
import com.dalong.elmbusiness.utils.DataUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import static com.dalong.elmbusiness.R.id.goodsList;


/**
 * 商品
 */
public class GoodsFragment extends BaseFragment implements StickyListHeadersListView.OnHeaderClickListener, StickyListHeadersListView.OnStickyHeaderChangedListener, StickyListHeadersListView.OnStickyHeaderOffsetChangedListener, GoodsListAdapter.OnShopCartGoodsChangeListener {


    private ListView mGoodsCateGoryList;
    private StickyListHeadersListView mGoodsList;
    private GoodsCategoryListAdapter mGoodsCategoryListAdapter;
    //商品类别列表
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity> goodscatrgoryEntities;
    //商品列表
    private List<GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity> goodsitemEntities;

    //存储含有标题的第一个含有商品类别名称的条目的下表
    private List<Integer> titlePois = new ArrayList<>();
    private GoodsListAdapter mGoodsListAdapter;
    private MyOnGoodsScrollListener myOnGoodsScrollListener;
    //上一个标题的小标
    private int lastTitlePoi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_goods, container, false);
        initView(view);
        initListener();
        initData();
        return view;
    }

    private void initData() {
        goodscatrgoryEntities = new ArrayList<>();
        goodsitemEntities = new ArrayList<>();
        GoodsListBean dataList=  DataUtils.GsonToBean(DataUtils.data,GoodsListBean.class);
        int i = 0;
        int j = 0;
        boolean isFirst;
        for (GoodsListBean.DataEntity dataItem : dataList.getData()) {
            goodscatrgoryEntities.add(dataItem.getGoodscatrgory());
            isFirst = true;
            for (GoodsListBean.DataEntity.GoodscatrgoryEntity.GoodsitemEntity goodsitemEntity :
                    dataItem.getGoodscatrgory().getGoodsitem()
                    ) {
                if (isFirst) {
                    titlePois.add(j);
                    isFirst = false;
                }
                j++;
                goodsitemEntity.setId(i);
                goodsitemEntities.add(goodsitemEntity);
            }
            i++;
        }

        mGoodsCategoryListAdapter = new GoodsCategoryListAdapter(goodscatrgoryEntities, getActivity());
        mGoodsCateGoryList.setAdapter(mGoodsCategoryListAdapter);

        mGoodsListAdapter = new GoodsListAdapter(goodsitemEntities, getActivity(), goodscatrgoryEntities);
        mGoodsListAdapter.setmActivity(getActivity());
        mGoodsList.setAdapter(mGoodsListAdapter);
        mGoodsListAdapter.setOnShopCartGoodsChangeListener(this);
        myOnGoodsScrollListener = new MyOnGoodsScrollListener();
        mGoodsList.setOnScrollListener(myOnGoodsScrollListener);
    }


    private void initView(View view) {
        mGoodsCateGoryList = (ListView)view.findViewById(R.id.goods_category_list);
        mGoodsList = (StickyListHeadersListView)view.findViewById(goodsList);
    }


    private void initListener() {
        mGoodsList.setOnHeaderClickListener(this);
        mGoodsList.setOnStickyHeaderChangedListener(this);
        mGoodsList.setOnStickyHeaderOffsetChangedListener(this);
        mGoodsList.setDrawingListUnderStickyHeader(true);
        mGoodsList.setAreHeadersSticky(true);
        mGoodsCateGoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGoodsList.setSelection(titlePois.get(position));
            }
        });
    }



    @Override
    public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {

    }

    @Override
    public void onStickyHeaderChanged(StickyListHeadersListView l, View header, int itemPosition, long headerId) {

    }

    @Override
    public void onStickyHeaderOffsetChanged(StickyListHeadersListView l, View header, int offset) {

    }

    @Override
    public void onNumChange() {

    }

    /**
     * 处理滑动 是两个ListView联动
     */
    private class MyOnGoodsScrollListener implements AbsListView.OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (!(lastTitlePoi == goodsitemEntities
                    .get(firstVisibleItem)
                    .getId())) {
                lastTitlePoi = goodsitemEntities
                        .get(firstVisibleItem)
                        .getId();
                mGoodsCategoryListAdapter.setCheckPosition(goodsitemEntities
                        .get(firstVisibleItem)
                        .getId());
                mGoodsCategoryListAdapter.notifyDataSetChanged();
            }
        }
    }


    /**
     * 添加 或者  删除  商品发送的消息处理
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GoodsListEvent event) {
        if(event.buyNums.length>0){
            for (int i=0;i<event.buyNums.length;i++){
                goodscatrgoryEntities.get(i).setBugNum(event.buyNums[i]);
            }
            mGoodsCategoryListAdapter.changeData(goodscatrgoryEntities);
        }

    }

}
