# payment-switchwon

### 개발환경
- Spring Boot 3.2.3
- Java 17
- Spring Data JPA
- H2

### Rest API
- 테스트 가능한 userId : 12345
- 테스트 가능한 merchantId : merchantId123
- swagger-ui : http://localhost:8080/swagger-ui.html

[잔액 조회]
- GET http://localhost:8080/api/payment/balance/{userId}

[결제 예상 결과 조회]
- POST http://localhost:8080/api/payment/estimate
  - -H ContentType: application/json

[결제 승인 요청]
- POST http://localhost:8080/api/payment/balance/{userId}
  - -H ContentType: application/json
