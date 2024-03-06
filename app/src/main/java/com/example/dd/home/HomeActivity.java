package com.example.dd.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dd.R;
import com.example.dd.home.dto.HistoryItem;
import com.example.dd.home.dto.HorizontalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private List<HistoryItem> historyItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.historyRecyclerView);
        ImageView leftArrow = findViewById(R.id.leftArrow);
        ImageView rightArrow = findViewById(R.id.rightArrow);





        // Create layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Add item decoration
        int horizontalSpaceWidth = getResources().getDimensionPixelSize(R.dimen.horizontal_space_width); // Define your desired space width
        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(horizontalSpaceWidth));


        // Initialize item list
        historyItemList = new ArrayList<>();
        // Add your items to the list
        historyItemList.add(new HistoryItem("Text AAAAAAAAsssssssssssssssssssssssssssssssssAA", R.drawable.login_image));
        historyItemList.add(new HistoryItem("Text BBBBBBBBsssssssssssssssssssssssssBBB", R.drawable.launch_img));
        historyItemList.add(new HistoryItem("Text CCCCCCCCCCC", R.drawable.login_image));
        historyItemList.add(new HistoryItem("Text D", R.drawable.launch_img));
        historyItemList.add(new HistoryItem("Text EEEEEEE", R.drawable.login_image));
        historyItemList.add(new HistoryItem("Text F", R.drawable.launch_img));
        // Add more items as needed

        // Create and set adapter
        historyAdapter = new HistoryAdapter(historyItemList);
        recyclerView.setAdapter(historyAdapter);

        // Set click listeners
        leftArrow.setOnClickListener(view -> {
            LinearLayoutManager layoutManagers = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (layoutManagers != null) {
                int firstVisibleItemPosition = layoutManagers.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition > 0) {
                    recyclerView.smoothScrollToPosition(firstVisibleItemPosition - 1);
                }
            }
        });

        rightArrow.setOnClickListener(view -> {
            LinearLayoutManager layoutManagers = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (layoutManagers != null) {
                int lastVisibleItemPosition = layoutManagers.findLastVisibleItemPosition();
                if (lastVisibleItemPosition < recyclerView.getAdapter().getItemCount() - 1) {
                    recyclerView.smoothScrollToPosition(lastVisibleItemPosition + 1);
                }
            }
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}