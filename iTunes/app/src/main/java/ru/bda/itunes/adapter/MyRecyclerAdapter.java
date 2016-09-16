package ru.bda.itunes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.bda.itunes.R;
import ru.bda.itunes.model.SearchAgent;

/**
 * Created by User on 16.09.2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<SearchAgent> agentList;

    public MyRecyclerAdapter(List<SearchAgent> agentList) {
        this.agentList = agentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchAgent agent = agentList.get(position);
        holder.tvName.setText(agent.getName());
        holder.tvDescription.setText(agent.getDescription());
    }

    @Override
    public int getItemCount() {
        return agentList.size();
    }

    public void setAgentList(List<SearchAgent> agentList) {
        this.agentList = agentList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivImage;
        public TextView tvName;
        public TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        }
    }
}
