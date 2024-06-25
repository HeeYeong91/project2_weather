package com.zerobase.weather.repository;

import com.zerobase.weather.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * 일기 리포지토리
 * @author 이희영
 */
@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    /**
     * 날짜로 모든 일기 조회
     * 
     * @param date 날짜
     * @return 일기 리스트
     */
    List<Diary> findAllByDate(LocalDate date);

    /**
     * 일정 기간 모든 일기 조회
     *
     * @param startDate 시작일
     * @param endDate 종료일
     * @return 일기 리스트
     */
    List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
