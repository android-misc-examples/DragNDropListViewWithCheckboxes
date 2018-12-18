package it.pgp.dragndroplistviewwithcheckboxes;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * This application creates a list view where the ordering of the data set
 * can be modified in response to user touch events.
 *
 * An item in the list view is selected via a long press event and is then
 * moved around by tracking and following the movement of the user's finger.
 * When the item is released, it animates to its new position within the list view.
 */
public class MainActivity extends Activity {

    DynamicListView listView;
    StableArrayAdapter adapter;

    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Item> itemList = new ArrayList<>();
        for (int i = 0; i < Items.itemStrings.length; ++i) {
            itemList.add(new Item(Items.itemStrings[i],true));
        }
        View.OnClickListener checkBoxListener = new View.OnClickListener() {

            public void onClick(View v) {
                View view = (View) v.getParent();
                int index = listView.indexOfChild(view);
                CheckBox checkBox = view.findViewById(R.id.itemCheckBox);
                adapter.updateItem(checkBox.isChecked(), index);
            }
        };

        adapter = new StableArrayAdapter(this, R.layout.item_view, itemList, checkBoxListener);
        listView = findViewById(R.id.listview);

        listView.setItemList(itemList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        r = ()->{
            try {
                for(;;) {
                    Collection<Item> items = adapter.getCurrentOrderingOfItems();
                    Thread.sleep(2000);
                    for (Item i : items) {
                        Log.d("---",i.label+"\t"+(i.selected?"True":"False"));
                    }
                    Log.d("---","***************");
                }
            } catch (InterruptedException ignored) {
            }
        };

        (new Thread(r)).start();
    }

}
