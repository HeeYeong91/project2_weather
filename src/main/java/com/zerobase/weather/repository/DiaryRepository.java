package com.zerobase.weather.repository;

import com.zerobase.weather.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 일기 리포지토리
 * @author 이희영
 */
@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
}
