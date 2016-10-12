package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by kh0ma on 12.10.16.
 */
@ActiveProfiles({Profiles.DATAJPA})
public class MealServiceDATAJPATest extends AbstractMealServiceTest {
}
