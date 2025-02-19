package DataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionVSCollections {

    public static void main(String[] args) {
        Collection c = new ArrayList();


        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(10);
        Collections.sort(list);


        Collection<Integer> collection = new ArrayList<>();
        collection.add(5);
        collection.add(10);

    }
}
