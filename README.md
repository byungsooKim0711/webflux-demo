# webflux-demo
웹플럭스 데모 프로젝트

# Database
``` bash
CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY
  , firstname VARCHAR(50)
  , lastname VARCHAR(50)
  , age INT
);
```

# RUN
``` bash
- npm install axios
- npm install vuex --save
- vuex install
- npm install vue-js-modal --save
- vue-js-modal install
- npm run dev (port: 80)
- ./mvnw spring-boot:run (port: 8080)
```

# [2019-02-12]
- mybatis, mariadb와 연동

# [2019-02-13]
- vue.js 연동
- axios 통신

# [2019-02-13]
- Read, Delete 완성

# [2019-02-13]
- docker + jenkins 테스트

# [2019-02-15]

.....

# [2019-02-27]
- H2 (In-Memory Database)로 테스트 설정환경 구성

# [2019-02-28]
- 이름 검색조건에 대한 테스트코드 추가
- ~ pact testing 공부중