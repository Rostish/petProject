package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Query("DELETE FROM Meal m WHERE m.user.id=:user_id and m.id=:id")
    int delete(@Param("id") int id,@Param("user_id") int user_id);

    Meal save (Meal meal, int userId);

    Optional<Meal> getByIdAndUserId(int id, int userId);

    List<Meal> findByDateTimeBetweenAndUserIdOrderByDateTimeDesc(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
