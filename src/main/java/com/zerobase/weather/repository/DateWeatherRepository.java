package com.zerobase.weather.repository;

import com.zerobase.weather.domain.DateWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 날씨 리포지토리
 * @author 이희영
 */
@Repository
public interface DateWeatherRepository extends JpaRepository<DateWeather, LocalDate> {

    /**
     * 날짜로 모든 날씨 찾기
     *
     * @param localDate 날짜
     * @return 날씨 리스트
     */
    List<DateWeather> findAllByDate(LocalDate localDate);
}
