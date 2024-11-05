package com.peisia.spring.mi.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.peisia.dto.GuestDto;
import com.peisia.dto.SearchDto;
import com.peisia.spring.mi.mapper.GuestMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/**
 * GuestServiceImpl 클래스
 * 
 * 이 클래스는 GuestService 인터페이스의 구현체로, 방문자(Guest) 관리에 대한 비즈니스 로직을 처리합니다.
 * Spring의 @Service 애너테이션을 사용하여 서비스 계층의 역할을 하도록 명시하였습니다.
 * 
 * @Log4j 애너테이션을 통해 로그를 기록하여 디버깅 및 오류 추적에 유용하게 활용할 수 있습니다.
 */
@Log4j // Log4j를 이용해 로깅 기능을 추가합니다.
@Service // Spring의 서비스 계층임을 명시합니다.
public class GuestServiceImpl implements GuestService {

	/**
	 * GuestMapper 객체의 의존성을 주입받아 데이터베이스 작업을 수행합니다.
	 * 
	 * @Setter(onMethod_ = @Autowired)를 사용하여 자동으로 setter 메서드를 생성하고,
	 *                   Spring의 @Autowired를 통해 GuestMapper 빈을 자동으로 주입받습니다.
	 */
	@Setter(onMethod_ = @Autowired) // 의존성 주입을 위해 Lombok의 @Setter와 Spring의 @Autowired를 결합하여 사용합니다.
	private GuestMapper mapper;

	@Override
	public Model getList(Model m, int currentPage) {
		// 한 페이지당 표시할 항목 수
		int listCountPerPage = 5;

		// 하나의 블록에 표시할 페이지 수
		int pagesPerBlock = 3;

		// 현재 페이지가 속한 블록 번호
		int currentBlock = 1;

		// 현재 블록의 시작 페이지 번호
		int blockStartPage = 1;

		// 현재 블록의 마지막 페이지 번호
		int blockEndPage = 1;

		// 전체 블록의 수 (총 페이지를 블록당 페이지 수로 나눈 값)
		int blockCount = 1;

		// 이전 페이지 번호 (필요 시 이전 페이지 이동을 위한 변수)
		int prevPage = 1;

		// 다음 페이지 번호 (필요 시 다음 페이지 이동을 위한 변수)
		int nextPage = 1;

		// 현재 페이지에서 가져올 데이터의 시작 인덱스를 계산합니다.
		int limitIndex = (currentPage - 1) * listCountPerPage;

		// 데이터베이스에서 지정된 인덱스부터 리스트 데이터를 가져옵니다.
		ArrayList<GuestDto> posts = mapper.getList(limitIndex);

		// 가져온 데이터의 사이즈를 확인하여 출력합니다.
		System.out.println("데이터확인 ------" + posts.size());

		// 가져온 리스트 모델을 추가하여 뷰에서도 사용할 수 있게 설정합니다.
		m.addAttribute("list", posts);

		// 데이터베이스에서 현재 페이지에 해당하는 데이터의 개수를 가져옵니다.
		int count = mapper.getCount(limitIndex);

		// 데이터 개수를 모델에 추가하려 뷰에서 사용할 수 있도록 설정합니다.
		m.addAttribute("count", count);

		// 총 페이지 수 구하기
		int totalPageCount = 0;
		// 총 페이지 수 = 전체 글 수 / 페이지 당 보여줄 글 수 , 단. 짜투리도 계산해야함.
		totalPageCount = (int) Math.ceil((double) count / listCountPerPage);
		log.info("--------방명록-------- : 총 게시글 수" + count);
		log.info("------- 방명록 ------- : 총 페이지 수" + totalPageCount);
		m.addAttribute("totalPageCount", totalPageCount);
		// 블럭 당 페이지 수 전달
		m.addAttribute("pagesPerBlock", pagesPerBlock);

		// 블럭 총 수
		blockCount = (int) Math.ceil((double) totalPageCount / pagesPerBlock);
		m.addAttribute("blockCount", blockCount);

		// 현재 페이지 번호로 현재 블럭번호 구하기
		// 공식 : 현재 블럭번호 = 현재 페이지 번호 / 블럭당 페이지 수 << 후 올림처리
		currentBlock = (int) Math.ceil((double) currentPage / pagesPerBlock);
		m.addAttribute("currentBlock", currentBlock);

		// 블럭 시작, 끝 페이지 구하기
		blockStartPage = (currentBlock - 1) * pagesPerBlock + 1;
		blockEndPage = currentBlock * pagesPerBlock;

		// 예외처리, 마지막 페이지보다 크면 마지막 페이지 값 전달
		if (blockEndPage > totalPageCount) {
			blockEndPage = totalPageCount;
		}

		m.addAttribute("blockStartPage", blockStartPage);
		m.addAttribute("blockEndPage", blockEndPage);

		// 현재 블록이 첫 번째 블록보다 크다면 (즉, 첫 블록이 아니라면)
		if (currentBlock > 1) {
			// 이전 블록이 존재함을 의미하는 속성을 Model에 추가
			m.addAttribute("hasBlockPrev", true);

			// 이전 블록의 첫 페이지를 계산하여 prevPage 변수에 저장
			// (currentBlock - 1) * pagesPerBlock + 1은 이전 블록의 첫 페이지 번호를 의미함
			prevPage = (currentBlock - 1) * pagesPerBlock + 1;

			// 다음 페이지 정보를 Model에 추가
			m.addAttribute("nextPage", nextPage);
		}

		return m;
	}

	/**
	 * 특정 방문자의 상세 정보를 조회하는 메서드
	 *
	 * @param bno - 조회할 방문자의 고유 번호
	 * @return GuestDto - 조회된 방문자의 상세 정보를 반환합니다.
	 */
	@Override
	public GuestDto read(long bno) {
		return mapper.read(bno); // Mapper를 통해 해당 방문자의 상세 정보를 조회합니다.
	}

	/**
	 * 특정 방문자를 삭제하는 메서드
	 *
	 * @param bno - 삭제할 방문자의 고유 번호
	 */
	@Override
	public void del(long bno) {
		mapper.del(bno); // Mapper를 통해 해당 방문자를 삭제합니다.
	}

	/**
	 * 새로운 방문자를 추가하는 메서드
	 *
	 * @param dto - 추가할 방문자의 정보를 포함한 GuestDto 객체
	 */
	@Override
	public void write(GuestDto dto) {
		mapper.write(dto); // Mapper를 통해 새로운 방문자 정보를 추가합니다.
	}

	/**
	 * 기존 방문자의 정보를 수정하는 메서드
	 *
	 * @param dto - 수정할 방문자의 정보를 포함한 GuestDto 객체
	 */
	@Override
	public void modify(GuestDto dto) {
		mapper.modify(dto); // Mapper를 통해 해당 방문자의 정보를 수정합니다.
	}

	@Override
	public Model listSearch(Model model, int currentPage, String search) {
		// 로그 출력: 서비스가 잘 넘어왔는지와 현재 페이지, 검색어를 로그에 표시
		log.info("------서비스 잘넘어옴 ------" + currentPage + search);

		// 검색에 사용할 SearchDto 객체 생성
		SearchDto x = new SearchDto();

		// 현재 페이지에 따른 조회 시작 인덱스 설정
		// 페이지당 5개의 항목을 보여준다고 가정하여 인덱스를 계산
		int t = (currentPage - 1) * 5;
		x.setLimitIndex(t); // 검색 시작 인덱스를 설정

		// 검색어를 SearchDto 객체에 설정
		x.setSearch(search);

		// 검색 조건에 맞는 결과 목록을 mapper를 통해 데이터베이스에서 가져옴
		ArrayList<GuestDto> posts = mapper.listSearch(x);

		// 디버깅 출력: 검색된 게시물의 수를 확인
		System.out.println("디버깅----------------------------" + posts.size());

		// 검색된 게시물 목록을 모델 객체에 추가하여 뷰에서 접근할 수 있도록 설정
		model.addAttribute("posts", posts);

		// 최종적으로 수정된 모델 객체 반환
		return model;
	}

}
