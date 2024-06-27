package com.zerobase.weather.error;

/**
 * Custom Exception - 날짜 에러
 * @author 이희영
 */
public class InvalidDate extends RuntimeException {

    private static final String MESSAGE = "너무 과거 혹은 미래의 날짜입니다.";

    public InvalidDate() {
        super(MESSAGE);
    }
}
