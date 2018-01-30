package com.woowahan.junweb;


import com.woowahan.junweb.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

//클라이언트 테스트 전에 서버를 띄워줌
@RunWith(SpringRunner.class)

//서버를 띄우기전에 열려있는 포트로 서버를 실행함.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    //포트정보랑 네트워크 정보를 가지고 있는 녀석.
    //나중에 이녀석을 활용하면 다른 경로나 url 을 대상으로 서비스를 테스트 할 수 있다.
    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createFrom() {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        log.debug("----------------------------------------------");
        System.out.println((response.getBody()));
    }

    @Test
    public void create() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("id", "4");
        params.add("userId", "javajigi2");
        params.add("password", "pass");
        params.add("name", "재성");
        params.add("email", "javajigi@slipp.net");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(params, headers);

        //포스트 메소드의 path설정
       ResponseEntity<String> response = template.postForEntity("/users", request, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));

        //리다이렉트 하는 부분분
       assertThat(response.getHeaders().getLocation().getPath(), is("/"));


       //user repository 의 내용이 들어갓는지 확인하는것.
        assertNotNull(userRepository.findUserByUserId("javajigi2"));
    }
}
