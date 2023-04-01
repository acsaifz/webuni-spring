package hu.webuni.hr.acsaifz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {
    @Autowired
    protected WebTestClient webTestClient;

    protected ResponseSpec doPostWithBody(String url, Object body){
        return webTestClient.post().uri(url).bodyValue(body).exchange();
    }

    protected ResponseSpec doGet(String url){
        return webTestClient.get().uri(url).exchange();
    }

    protected ResponseSpec doPutWithBody(String url, Object body){
        return webTestClient.put().uri(url).bodyValue(body).exchange();
    }

    protected ResponseSpec doDelete(String url){
        return webTestClient.delete().uri(url).exchange();
    }
}
