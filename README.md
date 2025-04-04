# 🔁 Sync vs Async 실험 프로젝트

Spring Boot 기반으로 동기(Sync)와 비동기(Async) 방식의 REST API 응답 차이를 실험한 프로젝트입니다.  
실제 응답 시간과 처리 흐름의 차이를 직접 체감해보고, 어떤 상황에서 비동기 처리가 유리한지 이해하는 데 목적이 있습니다.

---

## ✅ 프로젝트 정보

- **프로젝트명**: syncAsync
- **기술스택**: Java 21, Spring Boot 3.4.4, Spring Web, Lombok, Swagger
- **실험 목적**: 
  - 동기/비동기 응답 처리 구조의 차이를 이해하고
  - 사용자 응답 흐름과 시스템 처리 병렬성의 차이를 확인함

---

## 📦 주요 의존성

```groovy
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-devtools'
implementation 'org.projectlombok:lombok'
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0'
```

---

## 🔧 실행 방법

```bash
./gradlew bootRun
```

> 실행 후 Swagger로 접속:  
> [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧪 실험 API

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/test/sync` | 3초 대기 후 응답하는 동기 방식 |
| GET | `/test/async` | 비동기 방식으로 처리 (즉시 응답, 백그라운드에서 3초 작업) |

---

## 📊 실험 결과 비교

| 요청 방식 | 사용자 응답 속도 | 서버 쓰레드 활용 | 동시 요청 처리 |
|-----------|------------------|-------------------|----------------|
| Sync | 느림 (3초 대기) | 하나의 쓰레드 점유 | 불리함 (순차 처리) |
| Async | 빠름 (즉시 응답) | 별도 쓰레드 풀 사용 | 유리함 (병렬 처리) |

---

## 🧠 느낀 점: 비동기 처리의 진짜 의미는 응답 흐름의 차이에 있다

 처음에는 단순히 `Thread.sleep(3000)` 같은 작업을 비동기로 처리하는 게 어떤 의미가 있을지 의문이었다.  
하지만 직접 실험해보면서, **사용자 입장에서의 응답 시간과 서버 내부의 처리 흐름은 전혀 다를 수 있다**는 걸 깨달았다.

 동기 방식은 사용자와 서버가 하나의 흐름에 묶여 함께 대기해야 하지만,  
비동기 방식은 **사용자는 즉시 응답을 받고**, **서버는 별도의 쓰레드에서 처리를 계속 진행**한다.

 이 구조는 실시간 응답이 중요한 서비스나, 동시 요청이 많은 상황에서 큰 강점을 가진다.  
또한 외부 API 호출, 알림 전송처럼 시간이 걸리는 작업을 효율적으로 분리할 수 있다.

 결국 비동기의 핵심은 단순히 “빠른 응답”이 아니라,  
**전체 흐름을 분리하고 시스템의 병렬성과 유연성을 높이는 것**임을 직접 체감할 수 있었다.

---
