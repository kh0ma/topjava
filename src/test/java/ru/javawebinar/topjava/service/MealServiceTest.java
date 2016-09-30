package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.MEAL_USER;
import static ru.javawebinar.topjava.MealTestData.MEAL_USER_1;

/**
 * Created by kh0ma on 30.09.16.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    protected MealService service;

    protected MealRepository repository = new InMemoryMealRepositoryImpl();

    @Autowired
    private DbPopulator dbPopulator;



    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(100005, 100000);
        Meal expected = repository.get(100005, 100000);
        MATCHER.assertEquals(expected,meal);
/*        System.out.println("TESTED");
        System.out.println(meal);
        System.out.println("Expected");
        System.out.println(expected);*/
    }

    @Test
    public void delete() throws Exception {
        service.delete(100003,UserTestData.USER_ID);
        repository.delete(100003,UserTestData.USER_ID);

        Collection<Meal> all = service.getAll(UserTestData.USER_ID);
        Collection<Meal> expected = repository.getAll(UserTestData.USER_ID);
        MATCHER.assertCollectionEquals(expected,all);
        /*System.out.println("TESTED");
        all.forEach(System.out::println);
        System.out.println("Expected");
        expected.forEach(System.out::println);*/

    }

    @Test
    public void getBetweenDates() throws Exception {
        Collection<Meal> all = service.getBetweenDates(LocalDate.of(2016, 9, 29),LocalDate.of(2016, 9, 29),UserTestData.USER_ID);
        Collection<Meal> expected = repository.getBetween(LocalDateTime.of(LocalDate.of(2016, 9, 29), LocalTime.MIN),LocalDateTime.of(LocalDate.of(2016, 9, 29),LocalTime.MAX),UserTestData.USER_ID);
        MATCHER.assertCollectionEquals(expected,all);
/*        System.out.println("TESTED");
        all.forEach(System.out::println);
        System.out.println("Expected");
        expected.forEach(System.out::println);*/
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        Collection<Meal> all = service.getBetweenDateTimes(LocalDateTime.of(2016, 9, 29, 7, 0, 0, 0),LocalDateTime.of(2016, 9, 30, 15, 0, 0, 0),UserTestData.USER_ID);
        Collection<Meal> expected = repository.getBetween(LocalDateTime.of(2016, 9, 29, 7, 0, 0, 0),LocalDateTime.of(2016, 9, 30, 15, 0, 0, 0),UserTestData.USER_ID);
        MATCHER.assertCollectionEquals(expected,all);
/*        System.out.println("TESTED");
        all.forEach(System.out::println);
        System.out.println("Expected");
        expected.forEach(System.out::println);*/
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> all = service.getAll(UserTestData.USER_ID);
        Collection<Meal> expected = repository.getAll(UserTestData.USER_ID);
        MATCHER.assertCollectionEquals(expected,all);
        /*System.out.println("TESTED");
        all.forEach(System.out::println);
        System.out.println("Expected");
        expected.forEach(System.out::println);*/

    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(MEAL_USER_1);
        updated.setDescription(updated.getDescription() + " +++Обновлено+++");
        updated.setCalories(updated.getCalories()+77);
        updated.setDateTime(LocalDateTime.now());

        service.update(updated,UserTestData.USER_ID);
        Meal meal = service.get(updated.getId(), UserTestData.USER_ID);
        MATCHER.assertEquals(updated, meal);

/*        System.out.println("TESTED");
        System.out.println(meal);
        System.out.println("Expected");
        System.out.println(updated);*/
    }

    @Test
    public void save() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Meal newMealJDBC = new Meal(now,"+++Новая еда+++",200);
        Meal createdJDBC = service.save(newMealJDBC,UserTestData.USER_ID);
        //newMealJDBC.setId(createdJDBC.getId());
        System.out.println(createdJDBC);

        Meal newMealInMemory = new Meal(now,"+++Новая еда+++",200);
        Meal createdInMemory = repository.save(newMealInMemory,UserTestData.USER_ID);
        //newMealInMemory.setId(createdJDBC.getId());
        System.out.println(createdInMemory);

        Collection<Meal> all = service.getAll(UserTestData.USER_ID);
        Collection<Meal> expected = repository.getAll(UserTestData.USER_ID);
        MATCHER.assertCollectionEquals(expected,all);
        System.out.println("TESTED");
        all.forEach(System.out::println);
        System.out.println("Expected");
        expected.forEach(System.out::println);
    }

}