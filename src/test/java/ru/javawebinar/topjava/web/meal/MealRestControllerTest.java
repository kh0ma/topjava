package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by kh0ma on 03.11.16.
 */
public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/rest/meals/";
    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + "100000/" + "100002"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        Integer userId = 100000;
        List<Meal> expected = Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2);
        mockMvc.perform(delete(REST_URL+ userId.toString() + "/" + "100002"))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(expected, mealService.getAll(userId));
    }

    @Test

    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + "100000/" + "all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(MEALS));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "ЗавтракUpdated", 500);
        Integer mealId = 100002;
        Integer userId = 100000;
        mockMvc.perform(put(REST_URL + userId + "/update/?id=" + mealId).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, mealService.get(mealId,userId));
    }

    @Test
    public void testCreate() throws Exception {
        Meal created = new Meal(of(2016, Month.MAY, 30, 12, 0), "ЗавтракUpdated", 500);
        Integer userId = 100000;
        List<Meal> expected = Arrays.asList(created,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1);
        mockMvc.perform(put(REST_URL + userId + "/create").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andDo(print())
                .andExpect(status().isOk());
        created.setId(100010);
        MATCHER.assertCollectionEquals(expected, mealService.getAll(userId));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "100000/" + "between" +
                "?startDateTime=2015-05-30T11:00:00&" +
                "endDateTime=2015-07-31T13:00:00"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(MEAL5,MEAL2)));
    }

}