package com.konstantin_romashenko.doyouknow.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.konstantin_romashenko.doyouknow.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder>
{
    private Context context;
    private RecOnClickListener recOnClickListener;
    private List<ListItem> listItemArray;
    private SharedPreferences pref;
    public DataAdapter(Context context, RecOnClickListener recOnClickListener, List<ListItem> listItemArray)
    {
        this.context = context;
        this.recOnClickListener = recOnClickListener;
        this.listItemArray = listItemArray;
        pref = context.getSharedPreferences("CAT",Context.MODE_PRIVATE);
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.DataHolder holder, int position)
    {
        holder.setData(listItemArray.get(position));

    }

    @Override
    public int getItemCount()
    {
        return listItemArray.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private boolean isFavChecked = false;
        private TextView tvText;
        private ImageButton imButFav;
        public DataHolder(View itemView)
        {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
            imButFav = itemView.findViewById(R.id.ibBut);
            imButFav.setOnClickListener(this);


        }

        public void setData(ListItem item)
        {
            tvText.setText(item.getText());
            setFav(item, getAdapterPosition());
        }

        @Override
        public void onClick(View view)
        {
            isFavChecked = !isFavChecked;
            if(isFavChecked)
            {
                imButFav.setImageResource(android.R.drawable.btn_star_big_on);
            }
            else
            {
                imButFav.setImageResource(android.R.drawable.btn_star_big_off);
            }
            recOnClickListener.onItemClicked(getAdapterPosition());

        }

        private void setFav(ListItem item, int position)
        {
            String fav_data = pref.getString(item.getCat(), "none");
            if(fav_data != null && fav_data != "none")
            {
                char[] charArray = fav_data.toCharArray();
                switch(charArray[item.getPosition()])
                {
                    case '0':
                        imButFav.setImageResource(android.R.drawable.btn_star_big_off);
                        isFavChecked = false;
                        break;
                    case '1':
                        imButFav.setImageResource(android.R.drawable.btn_star_big_on);
                        isFavChecked = true;
                        break;
                }
            }


        }
    }

    public void updateList(List<ListItem> listArray)
    {
        listItemArray.clear();
        listItemArray.addAll(listArray);
        notifyDataSetChanged();
    }
}


//@android:drawable/btn_star_big_on
//@android:drawable/btn_star_big_off