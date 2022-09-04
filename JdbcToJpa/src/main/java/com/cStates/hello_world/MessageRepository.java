package com.cStates.hello_world;

import org.springframework.data.repository.CrudRepository;

// 데이터 액세스 계층에서 데이터베이스와 연동 담당
// CrudRepository: 데이터베이스에 CRUD(데이터 생성, 조회, 수정, 삭제)를 위해 Spring에서 지원해주는 인터페이스
// <>를 지정해주어 Message Entity object에 담긴 데이터를 DB 테이블에 생성 | 수정
// DB에서 조회한 데이터를 MessageEntityClass로 변환할 수 있음
public interface MessageRepository extends CrudRepository<Message, Long> {

    // 내부에 아무 코드도 없지만 서비스 계층에서 DI를 통해 주입받은 후 DB 작업을 위해 사용됨

}
