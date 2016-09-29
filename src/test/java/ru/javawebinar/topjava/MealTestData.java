package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.IndexUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {


    public static final Meal MEAL_USER_1 = new Meal(LocalDateTime.of(2016, 9, 29, 8, 0, 0, 0), "Завтрак", 400);
    public static final Meal MEAL_USER_2 = new Meal(LocalDateTime.of(2016, 9, 29, 12, 0, 0, 0), "Обед", 600);
    public static final Meal MEAL_USER_3 = new Meal(LocalDateTime.of(2016, 9, 29, 17, 0, 0, 0), "Ужин", 500);
    public static final Meal MEAL_USER_4 = new Meal(LocalDateTime.of(2016, 9, 30, 9, 0, 0, 0), "Завтрак", 700);
    public static final Meal MEAL_USER_5 = new Meal(LocalDateTime.of(2016, 9, 30, 14, 0, 0, 0), "Обед", 800);
    public static final Meal MEAL_USER_6 = new Meal(LocalDateTime.of(2016, 9, 30, 19, 0, 0, 0), "Ужин", 520);

    public static final Meal MEAL_ADMIN_1 = new Meal(LocalDateTime.of(2016, 9, 29, 8, 0, 0, 0), "Админ завтрак", 400);
    public static final Meal MEAL_ADMIN_2 = new Meal(LocalDateTime.of(2016, 9, 29, 12, 0, 0, 0), "Админ обед", 600);
    public static final Meal MEAL_ADMIN_3 = new Meal(LocalDateTime.of(2016, 9, 29, 17, 0, 0, 0), "Админ ужин", 500);
    public static final Meal MEAL_ADMIN_4 = new Meal(LocalDateTime.of(2016, 9, 30, 9, 0, 0, 0), "Админ завтрак", 700);
    public static final Meal MEAL_ADMIN_5 = new Meal(LocalDateTime.of(2016, 9, 30, 14, 0, 0, 0), "Админ обед", 800);
    public static final Meal MEAL_ADMIN_6 = new Meal(LocalDateTime.of(2016, 9, 30, 19, 0, 0, 0), "Админ ужин", 520);

    public static final List<Meal> MEAL_USER = Arrays.asList(MEAL_USER_1, MEAL_USER_2, MEAL_USER_3, MEAL_USER_4, MEAL_USER_5, MEAL_USER_6);
    public static final List<Meal> MEAL_ADMIN = Arrays.asList(MEAL_ADMIN_1, MEAL_ADMIN_2, MEAL_ADMIN_3, MEAL_ADMIN_4, MEAL_ADMIN_5, MEAL_ADMIN_6);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                    )
    );

}
