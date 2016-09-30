package ru.javawebinar.topjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.IndexUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * User: gkislin
 * Date: 22.08.2014
 */

public class SpringMain {




    @Autowired
    private static DbPopulator dbPopulator;

    public static void main(String[] args) {
        /*//**//*//* java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(UserTestData.USER);
            System.out.println();

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            List<MealWithExceed> filteredMealsWithExceeded =
                    mealController.getBetween(
                            LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
            filteredMealsWithExceeded.forEach(System.out::println);*//**//*

        System.out.println(IndexUtil.indexCounter());
        System.out.println(IndexUtil.indexCounter());
        System.out.println(IndexUtil.indexCounter());*/

        /*InMemoryMealRepositoryImpl test = new InMemoryMealRepositoryImpl();

        test.getAll(USER_ID).forEach(System.out::println);
        System.out.println();
        test.getAll(ADMIN_ID).forEach(System.out::println);*/

        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml");
            MealService service = appCtx.getBean(MealService.class);

        DbPopulator dbPopulator = appCtx.getBean(DbPopulator.class);
        dbPopulator.execute();


        LocalDateTime now = LocalDateTime.now();
        Meal newMealJDBC = new Meal(now,"+++Новая еда+++",200);
        Meal createdJDBC = service.save(newMealJDBC,UserTestData.USER_ID);
        newMealJDBC.setId(createdJDBC.getId());



        Collection<Meal> all = service.getAll(UserTestData.USER_ID);


        System.out.println("TESTED");
        all.forEach(System.out::println);

    }
}

