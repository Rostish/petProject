package ru.javawebinar.topjava.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;
import ru.javawebinar.topjava.service.MealService;

import static ru.javawebinar.topjava.MealTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
    @Autowired
    private MealService mealService;

//    @Test
//    @Transactional
//    public void getWithUser() throws Exception {
//        assertThat(mealService.getWithUser(MEAL1_ID, USER_ID).getUser())
//                .isEqualToIgnoringGivenFields("registered", "roles");
//    }
}
