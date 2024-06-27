package com.zerobase.weather.service;

import com.zerobase.weather.domain.DateWeather;
import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.repository.DateWeatherRepository;
import com.zerobase.weather.repository.DiaryRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 일기 서비스
 * @author 이희영
 */
@Service
public class DiaryService {

    @Value("${openweathermap.key}")
    private String apiKey;

    private final DiaryRepository diaryRepository;

    private final DateWeatherRepository dateWeatherRepository;

    public DiaryService(DiaryRepository diaryRepository, DateWeatherRepository dateWeatherRepository) {
        this.diaryRepository = diaryRepository;
        this.dateWeatherRepository = dateWeatherRepository;
    }

    /**
     * 매일 새벽 1시에 날씨 데이터 가져오기
     */
    @Transactional
    @Scheduled(cron = "0 0 1 * * *")
    public void saveWeatherDate() {
        dateWeatherRepository.save(getWeatherFromApi());
    }

    /**
     * 일기 생성
     * 
     * @param date 날짜
     * @param text 일기
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createDiary(LocalDate date, String text) {
        // 날씨 데이터 가져오기 (API에서 가져오기? or DB에서 가져오기?)
        DateWeather dateWeather = getDateWeather(date);
        
        // 우리 db에 넣기
        Diary nowDiary = new Diary();
        nowDiary.setDateWeather(dateWeather);
        nowDiary.setText(text);

        diaryRepository.save(nowDiary);
    }

    /**
     * 일기 조회
     *
     * @param date 날짜
     * @return 일기 리스트
     */
    @Transactional(readOnly = true)
    public List<Diary> readDiary(LocalDate date) {
        return diaryRepository.findAllByDate(date);
    }

    /**
     * 일정 기간 일기 조회
     *
     * @param startDate 시작일
     * @param endDate 종료일
     * @return 일기 리스트
     */
    @Transactional(readOnly = true)
    public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    /**
     * 일기 수정
     * 
     * @param date 일기 작성일
     * @param text 수정 내용
     */
    @Transactional
    public void updateDiary(LocalDate date, String text) {
        Diary nowDiary = diaryRepository.getFirstByDate(date);
        nowDiary.setText(text);
        diaryRepository.save(nowDiary);
    }

    /**
     * 일기 삭제
     * 
     * @param date 일기 작성일
     */
    @Transactional
    public void deleteDiary(LocalDate date) {
        diaryRepository.deleteAllByDate(date);
    }

    /**
     * 날씨 가져오기
     * 
     * @param date 날짜
     * @return 날씨
     */
    private DateWeather getDateWeather(LocalDate date) {
        List<DateWeather> dateWeatherListFromDB = dateWeatherRepository.findAllByDate(date);

        if (dateWeatherListFromDB.isEmpty()) {
            // 새로 api에서 날씨 정보를 가져와야 한다
            // 정책상,,, 현재 날씨를 가져오도록 하거나,, 날씨없이 일기를 쓰도록,,
            return getWeatherFromApi();
        } else {
            return dateWeatherListFromDB.get(0);
        }
    }

    /**
     * API로 날씨 데이터 가져오기
     *
     * @return 날씨
     */
    private DateWeather getWeatherFromApi() {
        // open weather map에서 날씨 데이터 가져오기
        String weatherData = getWeatherString();

        // 받아온 날씨 json 파싱하기
        Map<String, Object> parseWeather = parseWeather(weatherData);

        DateWeather dateWeather = new DateWeather();
        dateWeather.setDate(LocalDate.now());
        dateWeather.setWeather(parseWeather.get("main").toString());
        dateWeather.setIcon(parseWeather.get("icon").toString());
        dateWeather.setTemperature((Double) parseWeather.get("temp"));

        return dateWeather;
    }

    /**
     * API로 날씨 데이터 가져오기
     *
     * @return json
     */
    private String getWeatherString() {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            return response.toString();
        } catch (Exception e) {
            return "failed to get response";
        }
    }

    /**
     * 날씨 json 파싱
     *
     * @param jsonString json
     * @return 파싱된 Map
     */
    private Map<String, Object> parseWeather(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> resultMap = new HashMap<>();

        JSONObject mainData = (JSONObject) jsonObject.get("main");
        resultMap.put("temp", mainData.get("temp"));
        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));

        return resultMap;
    }

}
