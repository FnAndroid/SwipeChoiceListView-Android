package divin.customview.swipechoicelist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import divin.customview.R;

/**
 * Created by Divin on 2017/12/14.
 */

public class SwipeChoiceListAdapter extends BaseAdapter {
    private static final String TAG = "SwipeChoiceListAdapter";
    private Context context;
    private List<ItemDataBean> datas;
    private boolean check[];
    private boolean displayCheckBox;

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        //Log.i(TAG, "notifyDataSetChanged: " + datas.size());
        // TODO: 2017/12/14 查看这个size是否OK
        check = new boolean[datas.size()];
    }

    public SwipeChoiceListAdapter(Context context, List<ItemDataBean> datas) {
        //Log.i(TAG, "SwipeChoiceListAdapter: ");
        this.context = context;
        this.datas = datas;
        check = new boolean[datas.size()];
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (holder == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_swipe_choice, null);
            holder = new ViewHolder();
            holder.tvFileName = convertView.findViewById(R.id.tv_file_name);
            holder.tvFileMessage = convertView.findViewById(R.id.tv_file_message);
            holder.checkBox = convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvFileName.setText(datas.get(position).getFileName());
        holder.tvFileMessage.setText(datas.get(position).getFileMessage());
        if (displayCheckBox){
            holder.checkBox.setVisibility(View.VISIBLE);
        }else {
            holder.checkBox.setVisibility(View.GONE);
        }
        if (check[position]){
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private static class ViewHolder {
        protected CheckBox checkBox;
        protected TextView tvFileName;
        protected TextView tvFileMessage;
    }

    /*-------------事件通知-------------*/
    public void notifyOnLongPress(int position) {
        //Log.i(TAG, "notifyOnLongPress: " + position);

            check[position] = true;
            displayCheckBox = true;

    }
}
