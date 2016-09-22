package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.MealsUtil;

/**
 * GKislin
 * 06.03.2015.
 */
public class AuthorizedUser {

    private static int currentUserId;

    public static void setCurrentUserId(int currentUserId) {
        AuthorizedUser.currentUserId = currentUserId;
    }

    public static int id() {
        return currentUserId;
    }

    public static int getCaloriesPerDay() {
        return MealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
