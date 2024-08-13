# spring Calendar

---

## Calendar v1.0

## 정보

## 배포 주소

## 소개

## 프로젝트 소개

# 가이드

---

## 요구 사항

## 설치 및 실행

# Stacks

---

# 화면 구성

---

# API

---

| 기능        | Method | URL               | request | response | 상태코드       |
|-----------|--------|-------------------|---------|----------|------------|
| 일정 작성     | POST   | /events           | Body    | 등록 정보    | 200 : 정상작성 |
| 선택한 일정 조회 | GET    | /events/{eventid} | Param   | 단건 응답 정보 | 200 : 정상조회 |
| 일정 목록 조회  | GET    | /events           | Body    | 다건 응답 정보 | 200 : 정상조회 |
| 선택한 일정 수정 | PUT    | /events/{eventid} | Body    | 수정 정보    | 200 : 정상수정 |
| 선택한 일정 삭제 | DELETE | /events/{eventid} | Body    | -        | 200 : 정상삭제 |

# 주요 기능

---

## 일정 작성

- 할일, 담당자명, 비밀번호, 작성일, 수정일을 저장합니다.
- eventId는 자동으로 생성되어 관리됩니다.
- event 생성시 최종 수정일은 생성일자 입니다.
- 일정을 반환받아 확인이 가능합니다.

## 선택한 일정 조회

- eventId를 이용해서 단건의 일정을 조회할 수 있습니다.

## 일정 목록 조회

- YYYY-MM-DD형식의 최종 수정일을 바탕으로 조건에 맞는 일정을 전부 조회합니다.
- 입력된 담당자명과 동일한 필드를 갖는 Event를 조회합니다.
- 위 두 조건중 하나라도 충족하면 조회합니다.
- 수정일을 기준으로 내림차순 정렬합니다.

## 선택한 일정 수정

- 할일과 담당자명만 수정이 가능합니다.
- 수정 요구시 비밀번호를 전달합니다.
- 작성일은 고정이며 수정일은 수정 시점으로 변경합니다.

## 선택한 일정 삭제

- eventId로 특정한 일정을 삭제합니다.
- 삭제시 비밀번호를 전달합니다.

# ERD

---
![스크린샷 2024-08-12 오후 8 58 44](https://github.com/user-attachments/assets/9b7b3f9c-713c-48c9-9c95-9e483f1afa2e)

# SQL

---

```

CREATE TABLE Event(
    eventId INT AUTO_INCREMENT PRIMARY KEY,
    todo VARCHAR(300) NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    password VARCHAR(255) NOT NULL ,
    createDay DATE NOT NULL,
    updateDay DATE NOT NULL
);
```
