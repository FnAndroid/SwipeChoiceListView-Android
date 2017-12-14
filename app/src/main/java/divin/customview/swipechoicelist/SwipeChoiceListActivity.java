package divin.customview.swipechoicelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import divin.customview.R;

public class SwipeChoiceListActivity extends AppCompatActivity {
    private SwipeChoiceListView listView;
    private SwipeChoiceListAdapter adapter;
    private List<ItemDataBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_choice_list);
        initView();
        initData();
    }

    private void initView() {
        listView = findViewById(R.id.list_swipe_choice);
        adapter = new SwipeChoiceListAdapter(this, datas);
        listView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            String fileName = "file" + i;
            String fileMessage = "共" + new Random().nextInt() + "项";
            datas.add(new ItemDataBean(fileName, fileMessage));
        }
        adapter.notifyDataSetChanged();
    }
}
