package com.colud.jctl.adapters.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.colud.jctl.bean.FutureWeatherDemo;
import com.jctl.colud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jcty on 2017/3/13.
 */

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.MyHolderTop> {


    private List<FutureWeatherDemo> mList;
    private Context mContext;

    public FutureWeatherAdapter(Context mContext, List<FutureWeatherDemo> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @Override
    public MyHolderTop onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_weather_layout_future, parent, false);
        //		if (viewType == TYPE_TOP) {
        //			return new MyHolderTop(view);
        //		} else {
        //			//			View view = LayoutInflater.from(mContext).inflate(R.layout.furture_weather_item, parent, false);
        //			//			return new MyHolder(view);
        //		}
        return new MyHolderTop(view);
    }

    @Override
    public void onBindViewHolder(MyHolderTop holder, int position) {
        //		WeatherData.ResultBean data = list.get(position);
        FutureWeatherDemo demo = mList.get(position);
        if (holder instanceof MyHolderTop) {
            if (demo != null) {
                for (int i = 0; i < mList.size(); i++) {
                    ((MyHolderTop) holder).futureToday.setText(mList.get(position).getDay());
                    ((MyHolderTop) holder).futureDate.setText(mList.get(position).getDate());
                    ((MyHolderTop) holder).futureWeather.setText(mList.get(position).getWeather());
                }
            }

        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolderTop extends RecyclerView.ViewHolder {
        /**
         * future
         */
        @BindView(R.id.future_today)
        TextView futureToday;
        @BindView(R.id.future_date)
        TextView futureDate;
        @BindView(R.id.future_weather)
        TextView futureWeather;
        @BindView(R.id.future_img)
        ImageView futureImg;

        public MyHolderTop(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
