package com.nvoronin;

import edu.nikita.ds.App;

public class Main {

    public static void main(String[] args) {
        try {
            App.main(args);
        }
        catch(Exception e) {
            System.out.print(e.toString());
        }
    }
}
