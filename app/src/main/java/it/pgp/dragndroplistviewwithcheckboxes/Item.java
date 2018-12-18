package it.pgp.dragndroplistviewwithcheckboxes;

public class Item {
    public String label;
    public Boolean selected;

    public Item(String label, Boolean selected) {
        this.label = label;
        this.selected = selected;
    }

//    @Override
//    public String toString() {
//        return label+"\t"+(selected?"True":"False");
//    }
}
