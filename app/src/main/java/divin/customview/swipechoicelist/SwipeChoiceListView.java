package divin.customview.swipechoicelist;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;

import divin.customview.R;

/**
 * Created by Divin on 2017/12/14.
 */

public class SwipeChoiceListView extends ListView implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private static final String TAG = "SwipeChoiceListView";
    private GestureDetector gestureDetector;
    private SwipeChoiceListAdapter adapter;


    public SwipeChoiceListView(Context context) {
        super(context);

    }

    public SwipeChoiceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, this);
        setOnTouchListener(this);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        this.adapter = (SwipeChoiceListAdapter) adapter;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (longPressed) {
            //Log.i(TAG, "onTouch: " + (int) event.getX() + "  " + (int) event.getY() + "  ");
            int position = pointToPosition((int) event.getX(), (int) event.getY());
            if (position != selectedItem) {
                check(position);
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                longPressed = false;
            }
            //如果已经在listview的顶端以上了，就自动滚动一下listview
            int totalTop = (int) event.getY();
            //Log.i(TAG, "totalTop=" + totalTop);
            if (totalTop < 200) {
                if (position > 0) {
                    smoothScrollToPosition(position - 1);
                    check(position - 1);
                }
            }
            //如果一定到底端
            int totalBottom = getHeight() - totalTop;
            //Log.i(TAG, "totalBottom=" + totalBottom);
            if (totalBottom < 200) {
                if (position < getCount() - 1) {
                    smoothScrollToPosition(position + 1);
                    check(position + 1);
                }
            }
            return true;
        }
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //Log.i(TAG, "onDown: ");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //Log.i(TAG, "onShowPress: ");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //Log.i(TAG, "onSingleTapUp: ");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //Log.i(TAG, "onScroll: ");
        return false;
    }

    private boolean longPressed;
    private int selectedItem;

    @Override
    public void onLongPress(MotionEvent e) {
        //Log.i(TAG, "onLongPress: ");
        longPressed = true;
        selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        //Log.i(TAG, "onLongPress: " + selectedItem);
        showAndCheck(selectedItem);
    }

    private void check(int position) {
        //Log.i(TAG, "check: " + position);
        if (position < 0) {
            return;
        }
        //通知数据改变
        adapter.notifyOnLongPress(position);
        //设置当前行选定
        ViewGroup checkItem = (ViewGroup) getChildAt(position - getFirstVisiblePosition());
        if (checkItem == null) {
            return;
        }
        CheckBox checkBox = checkItem.findViewById(R.id.checkbox);
        checkBox.setChecked(true);
    }

    private void showAndCheck(int position) {
        //通知数据改变
        adapter.notifyOnLongPress(position);
        //设置当前行选定
        ViewGroup checkItem = (ViewGroup) getChildAt(position - getFirstVisiblePosition());
        CheckBox checkBox = checkItem.findViewById(R.id.checkbox);
        checkBox.setChecked(true);
        //显示所有行
        for (int i = 0; i <= getLastVisiblePosition() - getFirstVisiblePosition(); i++) {
            ViewGroup tempCheckItem = (ViewGroup) getChildAt(i);
            CheckBox tempCheckBox = tempCheckItem.findViewById(R.id.checkbox);
            tempCheckBox.setVisibility(VISIBLE);
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //Log.i(TAG, "onFling: ");
        return false;
    }


}
