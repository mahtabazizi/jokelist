package com.mahtab.jokes.ui.JokesList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahtab.jokes.R;
import com.mahtab.jokes.models.Value;

import java.util.List;

public class JokesListAdapter extends RecyclerView.Adapter<JokesListAdapter.ViewHolder> {
    private List<Value> data;
    private Context context;

    public JokesListAdapter(Context context, List<Value> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public JokesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_jokes, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(JokesListAdapter.ViewHolder holder, int position) {
        holder.txtJoke.setText(data.get(position).getJoke());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJoke;

        public ViewHolder(View itemView) {
            super(itemView);
            txtJoke = (TextView) itemView.findViewById(R.id.list_item_jokes_txt_joke);
        }
    }
}