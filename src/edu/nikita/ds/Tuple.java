package edu.nikita.ds;

/**
 * Created by nickvoronin on 6/21/14.
 */

public class Tuple {
    int x;
    int y;

    public Tuple(){

    }

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tuple){
           return obj.toString().equals(toString());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return Integer.toString(x) + "," + Integer.toString(y);
    }
}
