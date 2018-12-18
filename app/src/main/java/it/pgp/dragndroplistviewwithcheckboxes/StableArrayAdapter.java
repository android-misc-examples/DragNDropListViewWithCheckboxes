package it.pgp.dragndroplistviewwithcheckboxes;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StableArrayAdapter extends ArrayAdapter<Item> {

    private static class ViewHolder {
        private TextView textView;
        private CheckBox checkBox;
    }

    final int INVALID_ID = -1;
    final View.OnClickListener onClickListener;

    Map<Item, Integer> mIdMap = new HashMap<>();
    ViewHolder viewHolder;

    private List<Item> objects;

    public StableArrayAdapter(Context context, int textViewResourceId, List<Item> objects, View.OnClickListener onClickListener) {
        super(context, textViewResourceId, objects);
        this.onClickListener = onClickListener;
        this.objects = objects;
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    public Collection<Item> getCurrentOrderingOfItems() {
        return objects;
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        Item item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.item_view, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.itemTextView);
            viewHolder.checkBox = convertView.findViewById(R.id.itemCheckBox);
            viewHolder.checkBox.setOnClickListener(onClickListener);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Item item = getItem(position);

        if(item!=null) {
            viewHolder.textView.setText(item.label);
            viewHolder.checkBox.setChecked(item.selected);
        }

        return convertView;
    }

    public void updateItem(boolean isChecked, int index) {
        getItem(index).selected = isChecked;
    }

    @Override
    public boolean hasStableIds()
    {
        return android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }
}
