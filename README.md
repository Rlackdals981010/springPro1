# spring Calendar

---

## Calendar v1.0

## 정보

## 배포 주소

## 소개

## 프로젝트 소개

---

# 가이드

## 요구 사항

## 설치 및 실행

---

# Stacks

---

# 화면 구성

---

# API

| 기능        | Method | URL               | request | response | 상태코드       |
|-----------|--------|-------------------|---------|----------|------------|
| 일정 작성     | POST   | /events           | Body    | 등록 정보    | 200 : 정상작성 |
| 선택한 일정 조회 | GET    | /events/{eventid} | Param   | 단건 응답 정보 | 200 : 정상조회 |
| 일정 목록 조회  | GET    | /events/          | Param   | 다건 응답 정보 | 200 : 정상조회 |
| 선택한 일정 수정 | PUT    | /events/{eventid} | Body    | 수정 정보    | 200 : 정상수정 |
| 선택한 일정 삭제 | DELETE | /events/{eventid} | Body    | -        | 200 : 정상삭제 |

---

# 주요 기능

---

# ERD

![스크린샷 2024-08-12 오후 4 32 44](https://github.com/user-attachments/assets/b4951b52-9fd9-4d66-949f-40d2bbb914ed)

---

# SQL

```
CREATE TABLE Calender(
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Event(
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    todo VARCHAR(300) NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    password VARCHAR(255) NOT NULL ,
    update_day DATE NOT NULL
);
```
