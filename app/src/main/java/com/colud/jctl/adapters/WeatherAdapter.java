package com.colud.jctl.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.colud.jctl.bean.WeatherData;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jcty on 2017/3/13.
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<WeatherData> list;
    private Context mContext;

    public static final int TYPE_TOP = 1;
    public static final int TYPE_NORMAL = 2;


    public WeatherAdapter(Context mContext, ArrayList<WeatherData> mList) {
        this.mContext = mContext;
        this.list = mList;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_TOP : TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_weather_layout, parent, false);
        //		if (viewType == TYPE_TOP) {
        //			return new MyHolderTop(view);
        //		} else {
        //			//			View view = LayoutInflater.from(mContext).inflate(R.layout.furture_weather_item, parent, false);
        //			//			return new MyHolder(view);
        //		}
        return new MyHolderTop(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //		WeatherData.ResultBean data = list.get(position);
        //		if (holder instanceof MyHolderTop) {
        //			((MyHolderTop) holder).weatherName.setText(data.getToday().getCity());
        //			((MyHolderTop) holder).weatherTemp.setText(data.getSk().getTemp() + "℃");
        //			((MyHolderTop) holder).weatherWindDirection.setText(data.getSk().getWind_direction());
        //			((MyHolderTop) holder).humidity.setText(data.getSk().getHumidity());
        //
        //
        //			//			WeatherData.ResultBean.FutureBean future=data.getFuture().get(position);
        //			//			if(future!=null){
        //			//				for (int i=0;i<data.getFuture().size();i++){
        //			//					((MyHolderTop) holder).futureToday.setText(future.getWeek());
        //			//					((MyHolderTop) holder).futureDate.setText(future.getDataTime());
        //			//					((MyHolderTop) holder).futureWeather.setText(future.getWeather());
        //			//				}
        //			//			}
        //
        //		}
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolderTop extends RecyclerView.ViewHolder {
        /**
         * top
         */
        @BindView(R.id.weather_name)
        TextView weatherName;
        @BindView(R.id.weather_temp)
        TextView weatherTemp;
        @BindView(R.id.weather_wind_direction)
        TextView weatherWindDirection;
        @BindView(R.id.humidity)
        TextView humidity;
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
        //		public void bindItem(WeatherData weatherXmlData, int timeType) {
        //			if (weatherXmlData == null) {
        //				return;
        //			}
        //			showWeatherInfo(weatherXmlData, timeType);
        //		}
    }

}
