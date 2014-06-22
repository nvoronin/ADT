package edu.nikita.ds;

import java.util.ArrayList;
import java.util.Comparator;

public class QSort<T> {
	
	public void quickSort(ArrayList<T> a, int p, int r, Comparator<T> comparer)
    {
        if(p<r)
        {
            int q=partition(a,p,r, comparer);
            quickSort(a,p,q, comparer);
            quickSort(a,q+1,r, comparer);
        }
    }

    private int partition(ArrayList<T> a, int p, int r, Comparator<T> comparer) {

        T x = a.get(p);
        int i = p-1 ;
        int j = r+1 ;

        while (true) {
            i++;
            while (i < r && comparer.compare(a.get(i), x) < 0)
                i++;
            j--;
            while (j>p && comparer.compare(a.get(i), x) > 0)
                j--;

            if (i < j)
                swap(a, i, j);
            else
                return j;
        }
    }

    private void swap(ArrayList<T> a, int i, int j) {
        // TODO Auto-generated method stub
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
    
    public static String print(ArrayList a) {
    	StringBuffer sb = new StringBuffer();
    	for(Object i : a){
    		sb.append(i.toString());
    	}
    	return sb.toString();
    }
}
