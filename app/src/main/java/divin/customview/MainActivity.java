package divin.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import divin.customview.swipechoicelist.SwipeChoiceListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startSwipeChoiceListActivity();
        setTitle("自定义View和ViewGroup");
    }

    private void startSwipeChoiceListActivity() {
        Intent intent = new Intent(this, SwipeChoiceListActivity.class);
        startActivity(intent);
    }
}
