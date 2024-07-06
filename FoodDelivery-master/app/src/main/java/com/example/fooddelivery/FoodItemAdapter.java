package com.example.fooddelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodItemAdapter extends BaseAdapter {
    private Context context;
    private List<FoodItem> foodItemList;

    public FoodItemAdapter(Context context, List<FoodItem> foodItemList) {
        this.context = context;
        this.foodItemList = foodItemList;
    }

    @Override
    public int getCount() {
        return foodItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_food, parent, false);
        }

        FoodItem currentFoodItem = (FoodItem) getItem(position);

        ImageView foodImageView = convertView.findViewById(R.id.food_image);
        TextView foodNameTextView = convertView.findViewById(R.id.food_name);
        TextView foodDescriptionTextView = convertView.findViewById(R.id.food_description);
        TextView foodPriceTextView = convertView.findViewById(R.id.food_price);

        foodImageView.setImageResource(currentFoodItem.getImageResourceId());
        foodNameTextView.setText(currentFoodItem.getName());
        foodDescriptionTextView.setText(currentFoodItem.getDescription());
        foodPriceTextView.setText(String.format("$%.2f", currentFoodItem.getPrice()));

        return convertView;
    }
}
