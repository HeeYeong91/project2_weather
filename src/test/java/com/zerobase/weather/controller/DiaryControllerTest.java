package com.zerobase.weather.controller;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.service.DiaryService;
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
class DiaryControllerTest {

    @Autowired
    DiaryService diaryService;


    @Test
    @DisplayName("일기 생성")
    void createDiary() {
        //given
        LocalDate date = LocalDate.now();
        String text = "일기 내용";

        //when
        diaryService.createDiary(date, text);
        Diary diary = new Diary();
        diary.setDate(date);
        diary.setText(text);

        //then
        assertEquals(date, diary.getDate());
        assertEquals(text, diary.getText());
    }

    @Test
    @DisplayName("일기 조회")
    void readDiary() {
        //given
        LocalDate date = LocalDate.now();
        String text = "일기 내용";

        Diary diary = new Diary();
        diary.setDate(date);
        diary.setText(text);

        //when
        diaryService.createDiary(date, text);
        List<Diary> list = diaryService.readDiary(date);

        //then
        assertEquals(date, list.get(0).getDate());
        assertEquals(text, list.get(0).getText());
    }

    @Test
    @DisplayName("일정기간 일기 조회")
    void readDiaries() {
        //given
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now();
        String text = "일기 내용";

        Diary diary1 = new Diary();
        diary1.setDate(startDate);
        diary1.setText(text);

        Diary diary2 = new Diary();
        diary2.setDate(endDate);
        diary2.setText(text);

        //when
        diaryService.createDiary(startDate, text);
        diaryService.createDiary(endDate, text);
        List<Diary> list = diaryService.readDiarys(startDate, endDate);

        //then
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("일기 수정")
    void updateDiary() {
        //given
        LocalDate date = LocalDate.now();
        String text = "일기 내용";
        String updateText = "일기 내용 변경";

        Diary diary = new Diary();
        diary.setDate(date);
        diary.setText(text);

        //when
        diaryService.createDiary(date, text);
        diaryService.updateDiary(date, updateText);

        //then
        assertNotEquals(updateText, diary.getText());
    }

    @Test
    @DisplayName("일기 삭제")
    void deleteDiary() {
        //given
        LocalDate date = LocalDate.now();
        String text = "일기 내용";

        Diary diary = new Diary();
        diary.setDate(date);
        diary.setText(text);

        //when
        diaryService.createDiary(date, text);
        diaryService.deleteDiary(date);
        List<Diary> list = diaryService.readDiary(date);

        //then
        assertEquals(0, list.size());
    }
}