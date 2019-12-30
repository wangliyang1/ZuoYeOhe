package com.bawei.zuoyeohe.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zuoyeohe.R;
import com.bawei.zuoyeohe.model.bean.ShopBean;
import com.bawei.zuoyeohe.util.NetUtil;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<ShopBean.DataBean> data;
    public MyAdapter(List<ShopBean.DataBean> data) {
           this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.child, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShopBean.DataBean dataBean = data.get(position);
        holder.child_one.setText(dataBean.getGoods_english_name());
        holder.child_two.setText(dataBean.getCurrency_price());
        NetUtil.getInstance().getPhono(dataBean.getGoods_thumb(),holder.child_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCkickListener.onClick(dataBean.getGoods_english_name());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView child_image;
        private final TextView child_one;
        private final TextView child_two;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            child_image = itemView.findViewById(R.id.child_image);
            child_one = itemView.findViewById(R.id.child_one);
            child_two = itemView.findViewById(R.id.child_two);
        }
    }
    OnCkickListener onCkickListener;

    public void setOnCkickListener(OnCkickListener onCkickListener) {
        this.onCkickListener = onCkickListener;
    }

    public interface OnCkickListener{
        void onClick(String s);
    }
}
