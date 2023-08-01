# **🚀 WebFluxPlayGround**

![](https://img.shields.io/badge/Learning-Reactive%20Programming-blue) ![](https://img.shields.io/badge/Framework-Spring%20WebFlux-brightgreen) ![](https://img.shields.io/badge/Library-Reactor-orange)

이 저장소는 Reactive Programming, Reactor 라이브러리 및 Spring WebFlux에 대해 학습하고 실험하기 위해 만들어졌습니다.  
Spring 프레임워크를 사용하여 반응형 애플리케이션을 구축하는데 필요한 기본 개념, 기술, 원칙 및 모범 사례를 이해하고 습득하는 것이 목표입니다.

![everything is stream](https://user-images.githubusercontent.com/61622657/226096471-11456fc0-c5a2-40a9-bcf4-0e78cdd7fa00.png)

## **🎯Learning Goals**

-   Reactive Programming의 개념과 원칙에 대한 깊은 이해
-   Reactor 라이브러리를 효과적으로 사용하여 Reactive 데이터 파이프라인 생성 방법 습득
-   Spring Boot와 WebFlux를 활용한 Reactive 웹 애플리케이션 구축 능력 향상
-   R2DBC와 Spring Data JPA를 이용한 Reactive 데이터 액세스 통합 방법 익히기
-   캐싱 및 데이터 저장을 위한 Reactive Redis 활용 방안 탐구
-   Reactive 애플리케이션에서 Spring Security를 이용한 인증 및 권한 부여 구현 방법 습득
-   Actuator를 활용하여 Reactive 애플리케이션 모니터링 및 관리 능력 개발

## **📚 Features for Learning**

1.  **Reactive RESTful API**: Spring WebFlux와 Reactor를 사용하여 비동기 요청/응답 시나리오를 처리하는 RESTful API 구축
2.  **Reactive 데이터 액세스**: R2DBC와 Spring Data JPA를 사용하여 블로킹되지 않은 방식으로 데이터를 효율적으로 쿼리하고 조작하는 Reactive 데이터 액세스 방법 익히기
3.  **Reactive Redis**: 분산 시스템에서 Reactive 프로그래밍 활용을 보여주기 위해 데이터 저장 및 캐싱 메커니즘으로 Reactive Redis 통합
4.  **Reactive Spring Security**: Reactive 패러다임에 적합한 인증 및 권한 부여 구현을 위해 Spring Security 적용 방법 탐구
5.  **Actuator**: Reactive 시스템의 특별한 요구사항과 과제에 초점을 맞춰 Spring Boot Actuator를 활용하여 Reactive 애플리케이션 모니터링 및 관리 방법 연구

## 📦 Running Redis with Docker or Podman
이 프로젝트는 데이터 저장 및 캐싱에 Reactive Redis를 사용합니다.

이 프로젝트를 정상적으로 실행하려면 Redis 인스턴스가 필요합니다. 

아래의 명령어를 사용하여 Docker 또는 Podman으로 Redis를 로컬에 실행할 수 있습니다.

**Docker를 사용하여 Redis 실행하기**

```bash
docker run --name some-redis -p 6379:6379 -d redis:latest
```

**Podman을 사용하여 Redis 실행하기:**

```bash
podman run --name some-redis -p 6379:6379 -d docker.io/library/redis:latest
```

-p 6379:6379 : 호스트의 6379 포트를 컨테이너의 6379 포트에 바인딩하는 부분이 중요합니다. 

해당 프로젝트의 application.yml 설정값에 redis port 값과 일치시켜줘야 합니다.

### 📚 Log 경로

---

```text
resource/logback.xml 
```

logback 설정 커스텀하면됨

default는 `WebFluxLog/yyyy-MM-dd` 디렉토리에 `webfluxlog.log` 파일로 저장

## 📦 TestCode 실행시

- 반드시 Redis Container를 Run 상태로 두고 실행시켜주세요
