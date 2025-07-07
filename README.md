# Mission-Shoppingcart


# 1주차 미션 - 장바구니

## Mission Senario

백엔드 마스터가 되고 싶던 박호건씨는 인터넷 강의를 통해 실력을 키우고자 한다. 하지만 원하는 모든 강의를 들으려니 가격이 너무 비싸서 구입할 수가 없다. 박호건씨는 한정된 예산으로 담을 수 있는 경우의수를 따지기 위해 장바구니 프로그램을 개발하려고 한다.

## Mission Overview

장바구니 프로그램을 개발한다. 총 예산과 장바구니에 담을 강의 이름 목록을 입력하면 예산 초과 여부와 초과 금액을 출력한다. (응용) 총 예산과 별개로 강의 유형별 예산을 둔다. 입력을 받을 때 각 유형별 예산도 입력받은 후 이에 대한 초과 여부 및 초과 금액을 출력한다. (심화) 각 유형별로 프로모션이 존재한다. 프로모션의 조건에 맞을 경우 프로모션을 적용하여 가격을 계산한다.

## Mission Goals

* 자바의 문법과 사용에 익숙해진다.
* 추상화 및 의존성 분리 등을 통해 객체지향적으로 프로그램을 설계한다.
* 유지보수성과 가독성을 고려하여 코드를 작성한다.

## Study Documents

* [https://www.jetbrains.com/ko-kr/academy/student-pack/#students](https://www.jetbrains.com/ko-kr/academy/student-pack/#students)
* [https://github.com/moongua404/allrounder-study/blob/main/github/guide.md](https://github.com/moongua404/allrounder-study/blob/main/github/guide.md)
* [https://velog.io/@cher_blair/IntelliJ-코드-스타일-설정하기-codeFormatter](https://velog.io/@cher_blair/IntelliJ-%EC%BD%94%EB%93%9C-%EC%8A%A4%ED%83%80%EC%9D%BC-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0-codeFormatter)
* [https://gorilla-ohgiraffers.tistory.com/16](https://gorilla-ohgiraffers.tistory.com/16)
* [https://inpa.tistory.com/entry/JAVA-☕-객체-지향OOP-클래스-문법-💯-총정리](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EA%B0%9D%EC%B2%B4-%EC%A7%80%ED%96%A5OOP-%ED%81%B4%EB%9E%98%EC%8A%A4-%EB%AC%B8%EB%B2%95-%F0%9F%92%AF-%EC%B4%9D%EC%A0%95%EB%A6%AC)
* [https://velog.io/@wkdwoo/Primitive-type원시타입-vs.-Reference-type참조타입](https://velog.io/@wkdwoo/Primitive-type%EC%9B%90%EC%8B%9C%ED%83%80%EC%9E%85-vs.-Reference-type%EC%B0%B8%EC%A1%B0%ED%83%80%EC%9E%85)

## Mission Guide

1. [미션 저장소](https://github.com/allrounder-backend/mission-shoppingcart)를 Fork 한다.
2. Folk 한 저장소에서 브랜치를 만든 후 미션을 구현한다.
3. 구현이 끝난 후 제출할 내용을 main 브랜치로 옮긴다.
4. 미션 저장소에 PR(Pull Request)를 보낸다.

## Feature Requirements Details

강의 및 프로모션 목록은 다음과 같다.

### 강의 목록

| 강의 id | 강의명                                            | 유형   | 비용    |
| ------- | ------------------------------------------------- | ------ | ------- |
| 1       | 쿠버네티스 어나더 클래스                          | DevOps | 77,000  |
| 2       | 이론과 실습으로 실력이 높아지는 대세는 쿠버네티스 | DevOps | 44,000  |
| 3       | 비전공자도 이해할 수 있는 AWS 입문/실전           | DevOps | 66,000  |
| 4       | 처음하는 MongoDB와 NoSQL                          | DBMS   | 69,300  |
| 5       | 비전공자도 이해할 수 있는 DB 설계 입문/실전       | DBMS   | 66,000  |
| 6       | 데이터 분석을 위한 기초 SQL                       | DBMS   | 16,500  |
| 7       | 한 번에 끝내는 자바스크립트                       | Lang   | 35,200  |
| 8       | 실전 자바 - 기본편                                | Lang   | 44,000  |
| 9       | 코딩으로 학습하는 GoF의 디자인 패턴               | Lang   | 88,000  |
| 10      | 스프링 핵심 원리 - 기본편                         | F/W    | 88,000  |
| 11      | 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술         | F/W    | 99,000  |
| 12      | Spring Boot를 활용하여 채팅 플랫폼 만들어보기     | F/W    | 57,200  |
| 13      | 실전! 스프링 부트와 JPA 활용                      | F/W    | 88,000  |
| 14      | 실습으로 배우는 핵심 네트워크 기술                | CS     | 132,000 |
| 15      | 모든 개발자를 위한 HTTP 웹 기본 지식              | CS     | 44,000  |
| 16      | 그림으로 쉽게 배우는 운영체제                     | CS     | 77,000  |
| 17      | 외워서 끝내는 SSL과 최소한의 암호기술             | CS     | 44,000  |

### 프로모션 목록

| 유형   | 프로모션                                           |
| ------ | -------------------------------------------------- |
| DevOps | 모든 강의 10% 할인                                 |
| DBMS   | 강의 마다 5,000원 할인                             |
| Lang   | 2개 이상 구매 시 가장 가격이 저렴한 강의 하나 무료 |
| F/W    | 구매 금액이 90,000원을 넘을 경우 30,000원 할인     |
| CS     | 강의 3과목 이상 구매 시 강의 30% 할인              |

프로모션은 각 유형 내에서만 적용된다. (ex. Lang - 2개 이상 구매 시 가장 가격이 저렴한 강의 하나 무료 이면 Lang을 2개 이상 구매할 때 Lang 중에서 가장 가격이 저렴한 강의 하나가 무료이다. )

총 예산과 구입한 강의 목록을 입력하면 초과 여부를 출력한다.

* 가격은 정수로 입력받고, 강의 목록은 `,` 로 강의 id를 열거한다.
* 예산을 초과할 경우 초과 금액을 명시한다.

```bash
총 예산을 입력해 주세요.
> 250000

구입할 강의 목록을 입력해주세요.
> 1, 14, 17

예산을 초과했습니다. (초과 금액 3,000원)
```

```bash
총 예산을 입력해 주세요.
> 260000

구입할 강의 목록을 입력해주세요.
> 1, 14, 17

예산을 초과하지 않았습니다.
```

(응용) 유형별 예산을 별도로 입력받는다.

* 유형별 예산의 경우 `유형-가격` 의 형태로 입력하며 `,` 를 통해 여러 유형별 예산을 입력받는다.
* 예산을 초과했을 경우 초과 여부 및 금액을 유형별로 출력한다.

```bash
총 예산을 입력해 주세요.
> 500000

유형별 예산을 입력해 주세요.
> DevOps-200000,F/W-250000,CS-100000

구입할 강의 목록을 입력해주세요.
> 1, 3, 10, 11, 15, 16

예산을 초과했습니다.
 - 총 예산 : OK
 - DevOps : OK
 - F/W : OK
 - CS : 21,000원 초과
```

(심화) 프로모션을 적용하여 가격을 계산한다.

```bash
총 예산을 입력해 주세요.
> 400000

유형별 예산을 입력해 주세요.
> DevOps-50000,DBMS-50000,F/W-50000,CS-50000

구입할 강의 목록을 입력해주세요.
> 1, 6, 11, 12, 14, 15, 16

예산을 초과했습니다.
 - 총 예산 : OK
 - DevOps : 19,300원 초과
 - DBMS : OK
 - F/W : 76,200원 초과
 - CS : 127,100원 초과
```

다음과 같은 상황에서는 예외를 발생시켜 프로그램을 종료한다.

* 가격에 음수가 입력됨.
* 강의 목록에 없는 강의가 입력됨.
* …

```bash
총 예산을 입력해 주세요.
> -1

IllegalArgumentException : 올바른 금액을 입력해주세요.
```

기능 명세 및 테스트케이스에 정의되지 않은 예외적 상황은 자의적으로 판단하되 예외를 발생시킬 경우 그 사유를 명확하게 적시하며 프로그램을 종료시킨다.

## Programming Requirements Details

* 개발환경은 JDK 21을 사용한다.
* 프로그램 실행의 시작점은 `Application`의 `main()`이다.
* `build.gradle` 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
* 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
* 구글 스타일 가이드를 준수하며 코드를 작성한다.
  [https://google.github.io/styleguide/javaguide.html](https://google.github.io/styleguide/javaguide.html), 한국어 번역 [https://newwisdom.tistory.com/m/96](https://newwisdom.tistory.com/m/96)
  * 4.2 블럭 들여쓰기: +4 스페이스
    * 새 블록 또는 블록과 유사한 구조(block-like construct)가 열릴 때마다 들여쓰기가 네 칸씩 증가합니다. 블록이 끝나면 들여쓰기는 이전 들여쓰기 단계로 돌아갑니다. 들여쓰기 단계는 블록 전체의 코드와
      주석 모두에 적용됩니다.
  * 4.4 열 제한: 120
    * Java 코드의 열 제한은 120자입니다. "문자"는 유니코드 코드 포인트를 의미합니다.
  * 4.5.2 들여쓰기 지속은 최소 +8 스페이스
    * 줄 바꿈 시 그 다음 줄은 원래 줄에서 +8 이상 들여씁니다.
  * 4.6.1 수직 빈 줄
    * ...
      빈 줄은 가독성을 향상시키기 위해서라면 어디든(예를 들면 논리적으로 코드를 구분하기 위해 문장 사이) 사용 될 수 있습니다. 클래스의 첫 번째 멤버나 초기화(initializer) 또는 마지막 멤버 또는 초기화(
      initializer) 뒤의 빈 줄은 권장되지도 비권장하지도 않습니다.

      > 클래스의 첫 번째 멤버나 초기화(initializer) 앞에 있는 빈줄을 강제하지 않습니다.
      >

      ...
      변수명, 함수명도 컨벤션이다.
* 입력은 `api` 의 `Console.readline()` 을 활용한다.
* 테스트는 `api` 의 `TestEnvironment` 를 활용한다.
  * `TestEnvironment` 의 `runMain` 메서드에 실행할 내용을 구현한다.
  * `@Test` 의 `run()` 의 파라미터로 mocking할 입력들을 넣는다.
  * `@Test` 안에 있는 `output()` 은 출력 결과를 낚아챈다.
