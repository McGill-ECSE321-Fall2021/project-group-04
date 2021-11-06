package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

public class Services {

    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;

    }

}
