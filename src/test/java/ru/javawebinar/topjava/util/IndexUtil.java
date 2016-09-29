package ru.javawebinar.topjava.util;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by kh0ma on 29.09.16.
 */
public class IndexUtil {

    private static int counter;

    public static int indexCounter()
    {
        return START_SEQ + counter++;
    }
}
