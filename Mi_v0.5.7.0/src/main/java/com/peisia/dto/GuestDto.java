package com.peisia.dto;

import lombok.Data;

/**
 * GuestDto 클래스
 * 
 * 이 클래스는 방문자(Guest) 정보를 저장하는 데이터 전송 객체(Data Transfer Object, DTO)입니다. 주로 서비스
 * 계층과 데이터 계층 간의 데이터 전달에 사용됩니다. Lombok의 @Data 애너테이션을 통해 getter, setter,
 * toString, equals, hashCode 메서드를 자동으로 생성합니다.
 */
@Data
public class GuestDto {

	/**
	 * 방문자의 고유 번호 (Primary Key) 예: 방명록에서 각 방문자를 식별하는 용도로 사용됩니다.
	 */
	private Long bno;

	/**
	 * 방문자의 메시지 또는 텍스트 내용 예: 방명록에 작성된 글의 내용을 저장합니다.
	 */
	private String btext;
}
