package com.peisia.dto;

import lombok.Data;

@Data
public class SearchDto {

	// 검색을 위한 조회 시작 인덱스 페이지네이션을 위해 사용되며 특정 페이지의 시작위치를 나타냅니다.
	private int limitIndex;

	// 검색어 특정 키워드로 방문자 목록을 필터링할때 사용됩니다.
	private String search;

}
