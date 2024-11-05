package com.peisia.spring.mi.mapper;

import java.util.ArrayList;

import com.peisia.dto.GuestDto;
import com.peisia.dto.SearchDto;

/**
 * GuestMapper 인터페이스
 * 
 * 이 인터페이스는 MyBatis를 통해 데이터베이스와 상호작용하기 위한 Mapper로서, 방문자(Guest) 정보에 대한 CRUD 및 검색
 * 기능을 정의합니다. 데이터베이스의 각 쿼리는 구현 클래스에서 XML 파일 또는 어노테이션을 통해 작성됩니다.
 */
public interface GuestMapper {

	/**
	 * 방문자 목록을 조회하는 메서드
	 *
	 * @param limitIndex - 목록의 조회 시작 인덱스, 페이지네이션을 위해 사용됩니다.
	 * @return ArrayList<GuestDto> - 조회된 방문자 목록
	 */
	ArrayList<GuestDto> getList(int limitIndex);

	/**
	 * 특정 조건을 만족하는 방문자 수를 반환하는 메서드
	 *
	 * @param limitIndex - 조회 시작 인덱스, 페이지네이션에 사용됩니다.
	 * @return int - 특정 조건을 만족하는 방문자의 총 수
	 */
	int getCount(int limitIndex);

	/**
	 * 검색 조건에 맞는 방문자 목록을 조회하는 메서드
	 *
	 * @param dto - 검색 조건을 포함하는 SearchDto 객체
	 * @return ArrayList<GuestDto> - 검색된 방문자 목록
	 */
	ArrayList<GuestDto> listSearch(SearchDto dto);

	/**
	 * 특정 방문자의 상세 정보를 조회하는 메서드
	 *
	 * @param bno - 조회할 방문자의 고유 번호
	 * @return GuestDto - 조회된 방문자의 상세 정보
	 */
	public GuestDto read(long bno);

	/**
	 * 특정 방문자를 삭제하는 메서드
	 *
	 * @param bno - 삭제할 방문자의 고유 번호
	 */
	public void del(long bno);

	/**
	 * 새로운 방문자를 추가하는 메서드
	 *
	 * @param dto - 추가할 방문자의 정보를 포함한 GuestDto 객체
	 */
	public void write(GuestDto dto);

	/**
	 * 기존 방문자의 정보를 수정하는 메서드
	 *
	 * @param dto - 수정할 방문자의 정보를 포함한 GuestDto 객체
	 */
	public void modify(GuestDto dto);

}
