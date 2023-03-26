# spring_banner_library
스프링 배너를 커스텀할 수 있는 라이브러리

## 사용법
1. build.gradle의 repositories부분에 `maven { url = uri("https://jitpack.io") }` 추가
2. build.gradle의 dependencies 부분에 `implementation ("com.github.dolong2:spring_banner_library:v1.0.0")` 추가
3. yml이나 properties파일에 아래와 같은 코드 추가
   해당 설정이 없을시 기본 스프링 로그로 출력
  ```yml
  spring:
    banner:
      txt: 배너로 만들 텍스트
  ```
  ```properties
  spring.banner.txt = 배너로 만들 텍스트
  ```
4. SpringBootApplication의 main메서드를 사용 언어에 맞게 아래와 같이 수정
  ```kt
  fun main(args: Array<String>) {
      val application = BannerGenerator.customBanner(Application::class.java)
      application.run(*args)
  }
  ```
  ```java
  public static void main(String[] args) {
    private SpringApplication application = BannerGenerator.customBanner(Application::class.java);
    application.run(args);
  }
  ```
