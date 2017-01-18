package com.dalong.elmbusiness.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dalong.elmbusiness.R;
import com.dalong.elmbusiness.entity.Evaluate;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class EvaluateFragment extends Fragment {

    private String keywords;
    private StickyHeadersItemDecoration top;


    private List<Evaluate> getData() {

        List<Evaluate> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            Evaluate bean = new Evaluate();

            bean.setName(keywords + i);
            list.add(bean);
        }

        return list;
    }


    public static EvaluateFragment getInstance(String mTitle) {
        EvaluateFragment tabFragment = null;


        if (tabFragment == null) {
            tabFragment = new EvaluateFragment();
        }
        tabFragment.keywords = mTitle;
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        init(recyclerView);

        return view;
    }


    private void init(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAapter());
    }




    private class MyAapter extends RecyclerView.Adapter<MyAapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,
                    viewGroup, false);
            return new ViewHolder(view);
        }


        @Override public void onBindViewHolder(ViewHolder holder, int position) {

            Evaluate bean = getData().get(position);

            holder.textView.setText(bean.getName());

        }


        @Override public int getItemCount() {
            return getData().size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private final TextView textView;


            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击："+getPosition(),Toast.LENGTH_LONG).show();
            }
        }
    }
}

