package com.example.fooddelivery;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView foodListView;
    private FoodItemAdapter foodItemAdapter;
    private List<FoodItem> foodItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodListView = findViewById(R.id.food_list_view);

        foodItemList = new ArrayList<>();
        foodItemList.add(new FoodItem("Pizza", "Delicious cheese pizza with fresh toppings", 9.99, R.drawable.pizza));
        foodItemList.add(new FoodItem("Burger", "Juicy beef burger with lettuce, tomato, and cheese", 7.99, R.drawable.burger));
        foodItemList.add(new FoodItem("Sushi", "Fresh sushi with salmon and avocado", 12.99, R.drawable.sushi));
        foodItemList.add(new FoodItem("Pasta", "Creamy Alfredo pasta with chicken", 10.99, R.drawable.pasta));
        foodItemList.add(new FoodItem("Salad", "Healthy green salad with a variety of vegetables", 6.99, R.drawable.salad));

        foodItemAdapter = new FoodItemAdapter(this, foodItemList);
        foodListView.setAdapter(foodItemAdapter);
    }
}
