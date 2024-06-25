package com.zerobase.weather.service;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.repository.DiaryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DiaryServiceTest {

    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    @DisplayName("일기 생성")
    void createDiary() {
        //given
        Diary diary = new Diary();
        diary.setDate(LocalDate.now());
        diary.setWeather("맑음");
        diary.setTemperature(30.0);
        diary.setIcon("123D");
        diary.setText("일기 작성");
        
        //when
        diaryRepository.save(diary);
        Diary readDiary = diaryRepository.findById(diary.getId()).get();

        //then
        assertEquals("일기 작성", readDiary.getText());
    }

    @Test
    @DisplayName("일기 조회")
    void readDiary() {
        //given
        createDiary();
        LocalDate date = LocalDate.now();

        //when
        List<Diary> list = diaryRepository.findAllByDate(date);

        //then
        assertEquals(1, list.size());
    }
    
    @Test
    @DisplayName("일정 기간 일기 조회")
    void readDiarys() {
        //given
        createDiary();
        createDiary();

        //when
        List<Diary> list = diaryRepository.findAllByDateBetween(
                LocalDate.now().minusWeeks(1),
                LocalDate.now()
        );

        //then
        assertEquals(2, list.size());
    }
    
    @Test
    @DisplayName("일기 수정")
    void updateDiary() {
        //given
        Diary diary = new Diary();
        diary.setDate(LocalDate.now());
        diary.setWeather("맑음");
        diary.setTemperature(30.0);
        diary.setIcon("123D");
        diary.setText("일기 작성");

        //when
        diaryRepository.save(diary);
        Diary nowDiary = diaryRepository.getFirstByDate(diary.getDate());
        nowDiary.setText("일기 수정");
        diaryRepository.save(diary);
        Diary updateDiary = diaryRepository.getFirstByDate(diary.getDate());

        //then
        assertEquals("일기 수정", updateDiary.getText());
    }
    
    @Test
    @DisplayName("일기 삭제")
    void deleteDiary() {
        //given
        Diary diary = new Diary();
        diary.setDate(LocalDate.now());
        diary.setWeather("맑음");
        diary.setTemperature(30.0);
        diary.setIcon("123D");
        diary.setText("일기 작성");

        //when
        diaryRepository.save(diary);
        diaryRepository.deleteAllByDate(diary.getDate());
        List<Diary> list = diaryRepository.findAllByDate(diary.getDate());

        //then
        assertEquals(0, list.size());
    }
}