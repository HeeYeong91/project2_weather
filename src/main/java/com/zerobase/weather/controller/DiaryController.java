package com.zerobase.weather.controller;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 일기 컨트롤러
 * @author 이희영
 */
@Tag(name = "DiaryController", description = "일기 컨트롤러")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    /**
     * 일기 생성
     * 
     * @param date 작성일
     * @param text 일기 내용
     */
    @Operation(summary = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장합니다")
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "일기 작성 날짜", example = "2020-02-02")
            LocalDate date,
            @RequestBody
            @Parameter(description = "일기 내용", example = "오늘 일기 내용")
            String text
    ) {
        diaryService.createDiary(date, text);
    }

    /**
     * 일기 조회
     * 
     * @param date 작성일
     * @return 일기 리스트
     */
    @Operation(summary = "선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "조회할 일기 날짜", example = "2020-02-02")
            LocalDate date
    ) {
        return diaryService.readDiary(date);
    }

    /**
     * 일정기간 일기 조회
     * 
     * @param startDate 시작일
     * @param endDate 종료일
     * @return 일기 리스트
     */
    @Operation(summary = "일정 기간중의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "일기를 조회할 시작 날짜", example = "2020-02-01")
            LocalDate startDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "일기를 조회할 끝 날짜", example = "2020-02-28")
            LocalDate endDate
    ) {
        return diaryService.readDiaries(startDate, endDate);
    }

    /**
     * 일기 수정
     * 
     * @param date 작성일
     * @param text 수정 내용
     */
    @Operation(summary = "특정 날짜의 일기 텍스트 내용을 수정해서 DB에 저장합니다")
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "일기를 수정할 날짜", example = "2020-02-02")
            LocalDate date,
            @RequestBody
            @Parameter(description = "일기 수정 내용", example = "일기 수정했어")
            String text
    ) {
        diaryService.updateDiary(date, text);
    }

    /**
     * 일기 삭제
     * 
     * @param date 날짜
     */
    @Operation(summary = "특정 날짜의 일기를 삭제합니다")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Parameter(description = "일기를 삭제할 날짜", example = "2020-02-02")
            LocalDate date
    ) {
        diaryService.deleteDiary(date);
    }
}
