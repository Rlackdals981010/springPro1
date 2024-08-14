# spring Calendar

---

## Calendar v1.0

> 개발 기간 2024.08.12 ~ 2024.08.15

## 정보

개인용 Calendar입니다.

## 배포 주소

미배포

## 소개

[김창민](https://github.com/Rlackdals981010)

## 프로젝트 소개

- 개인용 일정을 저장하는 캘린더 입니다.
- 단일 일정에는 할일, 담당자명, 생성일, 최종 수정일이 저장됩니다.
- 작성, 단일조회, 범위조회, 수정, 삭제 기능을 지원합니다.

---

## 요구 사항

- 일정 작성
    - 할일, ~~담당자명~~ 담당자 ID, 비밀번호, 생성일, 최종 수정일을 저장합니다.
    - eventId는 자동으로 생성됩니다.
    - 최초 작성시 생성일과 수정일은 일치합니다.
    - 등록한 정보를 반환합니다.

- 선택한 일정 조회
    - 고유식별자를 이용해서 단건의 응답 정보를 조회합니다.

- 일정 목록 조회
    - YYYY-MM-DD 형식의 수정일을 통해 조회합니다.
    - ~~담당자명~~ 담당자 ID을 통해 조회합니다.
    - 위 2가지 조건중 하나라도 일치하면 조회합니다.
    - 수정일 기준으로 내림차순 정렬합니다.
- 선택한 일정 수정
    - 내용중 할일, 담당자 ID만 수정합니다.
    - 비밀번호를 전달합니다.
    - 작성일은 고정이며 수정일만 수정됩니다.
    - 수정된 일정의 정보를 반환받아 확인합니다.

- 선택한 일정 삭제
    - 선택한 일정을 삭제합니다.
    - 비밀번호를 함깨 전달합니다.

- +관리자를 추가합니다.
- +event들을 페이지로 관리합니다.
-

# Stacks

![자바](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![스프링](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![mysql](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

---

# API

---

## events

| 기능        | Method | URL               | request | response | 상태코드                            |
|-----------|--------|-------------------|---------|----------|---------------------------------|
| 일정 작성     | POST   | /events           | Body    | 등록 정보    | `200 : 정상작성`,`404 : 조회불가`       |
| 선택한 일정 조회 | GET    | /events/{eventid} | Param   | 단건 응답 정보 | `200 : 정상조회`,`404 : 조회불가`       |
| 일정 목록 조회  | GET    | /events           | query   | 다건 응답 정보 | `200 : 정상조회`,  `404 : 조회불가`     |
| 선택한 일정 수정 | PUT    | /events/{eventid} | Body    | 수정 정보    | `200 : 정상수정`,`403: 비밀번호 입력 오류`  |
| 선택한 일정 삭제 | DELETE | /events/{eventid} | Body    | 삭제 id    | `200 : 정상삭제`, `403: 비밀번호 입력 오류` |

## managers

| 기능         | Method | URL               | request | response | 상태코드         |
|------------|--------|-------------------|---------|----------|--------------|
| 관리자 추가     | POST   | /managers         | Body    | 등록 정보    | `200 : 정상작성` |
| 선택한 관리자 조회 | GET    | /managers/{manid} | Param   | 단건 응답 정보 | `200 : 정상조회` |
| 관리자 목록 조회  | GET    | /managers         | Body    | 다건 응답 정보 | `200 : 정상조회` |
| 선택한 관리자 수정 | PUT    | /managers/{manid} | Body    | 수정 id    | `200 : 정상수정` |
| 선택한 관리자 삭제 | DELETE | /managers/{manid} | Body    | 삭제 id    | `200 : 정상삭제` |

## pages

| 기능        | Method | URL              | request | response  | 상태코드         |
|-----------|--------|------------------|---------|-----------|--------------|
| 해당 페이지 조회 | GET    | /pages/{pagenum} | Param   | 단일 페이지 정보 | `200 : 정상조회` |

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
    eventId BIGINT AUTO_INCREMENT PRIMARY KEY,
    todo VARCHAR(300) NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    password VARCHAR(255) NOT NULL ,
    createDay DATE NOT NULL,
    updateDay DATE NOT NULL
);

CREATE TABLE Manager(
    manId VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) ,
    email  VARCHAR(50),
    createDay DATE NOT NULL,
    updateDay DATE NOT NULL
);
```
