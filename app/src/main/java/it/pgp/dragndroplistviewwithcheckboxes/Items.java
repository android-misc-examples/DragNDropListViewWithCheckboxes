package it.pgp.dragndroplistviewwithcheckboxes;

import java.util.ArrayList;

public class Items {

    public static final int num = 10; // checkbox state is still buggy if the items are too many, but it works with reasonable values
    public static final String[] itemStrings;

    static {
        ArrayList<String> a = new ArrayList<>();
        for (int i=0;i<num;i++)
            a.add("Item"+i);
        itemStrings = a.toArray(new String[0]);
    }

}
