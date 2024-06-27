# :sunny: 날씨 일기 프로젝트
<br />

## :page_facing_up: 목차
1. 프로젝트 소개
2. 프로젝트 기능
   * [1. 일기 생성 API](#1-일기-생성-API)
   * [2. 일기 조회 API](#2-일기-조회-API)
   * [3. 일정기간 일기 조회 API](#3-일정기간-일기-조회-API)
   * [4. 일기 수정 API](#4-일기-수정-API)
   * [5. 일기 삭제 API](#5-일기-삭제-API)
<br />

## :eyes: 1. 프로젝트 소개
2차 실습 프로젝트 Spring boot와 OpenWeatherMap API(Open Api)를 활용하여 일기를 작성하는 프로젝트 <br />

OpenWeatherMap Api를 이용해 날씨 데이터를 가져와서 <br />
날씨와 사용자가 작성한 일기 내용을 저장합니다. <br />
<br />

:smile: 개발 인원 : 1명 <br />
:calendar: 프로젝트 기간 : 2024년 6월 20일 ~ 2024년 6월 24일 <br />
:hammer: Tools <br /> 
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) 
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white) <br />
:books: languages & library <br />
![Spring](https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white) 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Junit5](https://img.shields.io/badge/Junit5-%23C21325?style=for-the-badge&logo=junit5&logoColor=white)
![Jpa](https://img.shields.io/badge/Jpa-%236DB33F.svg?style=for-the-badge)
![Lombok](https://img.shields.io/badge/Lombok-%23ffffff.svg?style=for-the-badge) <br />
:wrench: API <br />
![OpenWeatherMap Api](https://img.shields.io/badge/OpenWeatherMap%20Api-white?style=for-the-badge)

<br />

프로젝트 엔티티 구조
![다이어크램](https://github.com/HeeYeong91/project2_weather/assets/139057065/1444b052-3fc3-4b08-98cf-636eb35a1a3f) <br />

## :pushpin: 2. 프로젝트 기능 및 Api 명세
![api명세](https://github.com/HeeYeong91/project2_weather/assets/139057065/62773b8c-af60-4bd2-9a29-7d39dee37f0b) <br />

## 1. 일기 생성 API
Spring Scheduler를 이용해 매일 새벽 1시 Open Api를 통해 날씨 데이터를 가져와 DB에 저장 <br />
DB에서 현재 날짜의 날씨 정보를 가져와서 사용자가 입력한 일기 내용으로 일기 생성 <br />
![일기생성api](https://github.com/HeeYeong91/project2_weather/assets/139057065/8e9857b6-2a09-44a0-b63a-53b20ec8b262) <br />
![일기생성](https://github.com/HeeYeong91/project2_weather/assets/139057065/d374cdae-4fb6-45dc-a1a8-81e876925c62) <br />
<br />

## 2. 일기 조회 API
:exclamation: 정책 : <br />
너무 먼 날짜를 조회할 경우 - 조회 실패 ("너무 과거 혹은 미래의 날짜입니다.")<br />
![일기조회api](https://github.com/HeeYeong91/project2_weather/assets/139057065/8d5fa070-7ca5-4b74-8099-b3e0196476b2) <br />
![일기조회](https://github.com/HeeYeong91/project2_weather/assets/139057065/2a95e3b0-644e-4b71-bc74-fb8456c34012) <br />
<br />

## 3. 일정기간 일기 조회 API
![일정기간일기조회api](https://github.com/HeeYeong91/project2_weather/assets/139057065/f155cc59-f173-46a7-b83d-f5804b543d0b) <br />
![일정기간일기조회](https://github.com/HeeYeong91/project2_weather/assets/139057065/d49819bf-880a-46ed-908a-842d9073084f) <br />
<br />

## 4. 일기 수정 API
![일기수정api](https://github.com/HeeYeong91/project2_weather/assets/139057065/b606579a-b21a-4c83-a929-cb8206d0b223) <br />
![일기수정](https://github.com/HeeYeong91/project2_weather/assets/139057065/7c2bbad8-346a-4f8b-8d00-bc872e268bb7) <br />
<br />

## 5. 일기 삭제 API
![일기삭제api](https://github.com/HeeYeong91/project2_weather/assets/139057065/3ff9738a-8b6a-4547-9a2b-ff305690efd1) <br />
![일기삭제](https://github.com/HeeYeong91/project2_weather/assets/139057065/23b02bea-4bfb-4fe7-be4f-cf0352b247f1) <br />
<br />

