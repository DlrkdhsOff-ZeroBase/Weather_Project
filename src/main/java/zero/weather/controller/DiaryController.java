package zero.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zero.weather.domain.Diary;
import zero.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.*;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Operation(summary = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @PostMapping("/create/diary")
    void createDiary(
            @Parameter(name = "date", description = "일기를 작성한 날짜", example = "2024-01-01")
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody String text) {

        diaryService.createDiary(date, text);
    }

    @Operation(summary = "선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @Parameter(name = "date", description = "일기를 작성한 날짜", example = "2024-01-01")
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @Parameter(name = "startDate", description = "조회할 기간의 첫번째 날", example = "2024-01-01")
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

            @Parameter(name = "endDate", description = "조회할 기간의 마지막 날", example = "2024-12-31")
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @Operation(summary = "선택한 날짜의 일기를 수정")
    @PutMapping("/update/diary")
    void updateDiary(
            @Parameter(name = "date", description = "수정할 일기의 날짜", example = "2024-01-01")
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody String text) {

        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "선택한 날짜의 일기 삭제")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @Parameter(name = "date", description = "삭제하고 싶은 일기의 날짜", example = "2024-01-01")
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        diaryService.deleteDiary(date);
    }
}