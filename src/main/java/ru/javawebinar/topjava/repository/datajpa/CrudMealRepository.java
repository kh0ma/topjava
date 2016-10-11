package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gkislin
 * 02.10.2016
 */

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Override
    @Transactional
    Meal save(Meal meal);

    /*@Modifying
    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> findAll(@Param("userId") Integer userID);*/
    List<Meal> findAllByUserIdOrderByDateTimeDesc(Integer userID);

    /*@Modifying
    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.id=:id")
    List<Meal> get(@Param("id") Integer id,@Param("userId") Integer userId);*/
    List<Meal> getAllByIdAndUserId(Integer id, Integer userId);

    @Transactional
    /*@Modifying
    @Query("DELETE FROM Meal m WHERE m.user.id=:userId AND m.id=:id")
    int delete(@Param("id") Integer id,@Param("userId") Integer userId);*/
    int deleteByIdAndUserId(Integer id, Integer userId);

    //@Modifying
    //@Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Meal> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId, LocalDateTime startDate, LocalDateTime endDate);
}
