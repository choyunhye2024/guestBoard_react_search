package com.peisia.spring.mi.service;

import org.springframework.ui.Model;

import com.peisia.dto.GuestDto;

/**
 * GuestService 인터페이스
 * 
 * 이 인터페이스는 Guest 관리 기능을 제공하는 서비스 레이어의 기본 틀을 정의합니다. 구현 클래스는 이 인터페이스의 메서드를 구현하여,
 * 게스트(방문자) 관련 작업을 수행합니다. 주요 기능에는 목록 조회, 검색, 읽기, 삭제, 작성, 수정이 포함됩니다.
 */
public interface GuestService {

	/**
	 * 방문자 목록을 조회하는 메서드
	 *
	 * @param m           - Model 객체, 뷰로 데이터를 전달하기 위해 사용됩니다.
	 * @param currentPage - 현재 페이지 번호, 페이지네이션에 사용됩니다.
	 * @return Model - 방문자 목록이 포함된 Model 객체를 반환합니다.
	 */
	public Model getList(Model m, int currentPage);

	/**
	 * 검색된 방문자 목록을 조회하는 메서드
	 *
	 * @param model       - Model 객체, 뷰로 데이터를 전달하기 위해 사용됩니다.
	 * @param currentPage - 현재 페이지 번호, 페이지네이션에 사용됩니다.
	 * @param search      - 검색어, 특정 키워드로 검색을 수행합니다.
	 * @return Model - 검색된 방문자 목록이 포함된 Model 객체를 반환합니다.
	 */
	public Model listSearch(Model model, int currentPage, String search);

	/**
	 * 특정 방문자의 상세 정보를 조회하는 메서드
	 *
	 * @param bno - 조회할 방문자의 고유 번호
	 * @return GuestDto - 조회된 방문자의 상세 정보를 포함한 객체
	 */
	public GuestDto read(long bno);

	/**
	 * 특정 방문자를 삭제하는 메서드
	 *
	 * @param bno - 삭제할 방문자의 고유 번호
	 */
	public void del(long bno);

	/**
	 * 새로운 방문자를 작성하는 메서드
	 *
	 * @param dto - 작성할 방문자의 정보를 포함한 GuestDto 객체
	 */
	public void write(GuestDto dto);

	/**
	 * 기존 방문자의 정보를 수정하는 메서드
	 *
	 * @param dto - 수정할 방문자의 정보를 포함한 GuestDto 객체
	 */
	public void modify(GuestDto dto);

}
