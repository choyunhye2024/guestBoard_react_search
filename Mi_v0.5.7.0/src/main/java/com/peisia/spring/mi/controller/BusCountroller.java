package com.peisia.spring.mi.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.peisia.bus.BusDto;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
@RequestMapping("/test/*")
public class BusCountroller {

	@RequestMapping("/b")
	public void b(Model m) {
		//// 우리나라 공공 api ////
		// 인코딩 인증키
		String API_URL = "http://apis.data.go.kr/6280000/ICPubTrans_v2/Bus_Route_Section_OPHour?serviceKey=RXhzntPt1PRMOUZ5Mr9cfXj0%2FGCGy8lFQHKKkAfFdMWSbVhICGIxzIO6%2FK01boYmiFOjiKXWM4yJY2HuUdcTQw%3D%3D&pageNo=1&numOfRows=10&YMD=20220701&routeName=1";
		// * 주의 * https 아님 http 임. https 는 인증관련 복잡한 처리를 해야함.
		RestTemplate restTemplate = new RestTemplate();

		//// **** 중요 **** uri
		URI uri = null; // java.net.URI 임포트 하셈
		try {
			uri = new URI(API_URL); // URI 클래스는 URL에 대한 유효성 검사, 구성요소 추출, 보안(특수문자, 공백 처리 등)을 도와줌
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		String k = restTemplate.getForObject(uri, String.class); //
		log.info("====== 우리나라 버스정보나오나 " + k);

		BusDto b = restTemplate.getForObject(uri, BusDto.class);
		log.info("인천 버스정보 잘나오나========" + b.response.body.totalCount);
		String statData = b.response.body.items.get(0).statDate;
		String routeName = b.response.body.items.get(0).routeName;
		String sectionName = b.response.body.items.get(0).sectionName;
		String dara = String.format("인천버스정보입니다. %s, %s, %s", statData, routeName, sectionName);
		log.info(dara);

		m.addAttribute("statData", statData);
		m.addAttribute("routeName", routeName);
		m.addAttribute("sectionName", sectionName);
		m.addAttribute("dara", dara);

	}

}
