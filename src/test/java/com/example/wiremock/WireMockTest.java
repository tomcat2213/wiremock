package com.example.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


public class WireMockTest {

    static WireMockServer wireMockServer;

    @BeforeAll
    public static void initData () {
        wireMockServer = new WireMockServer(wireMockConfig().port(8099)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        configureFor("localhost",8099);
    }
    @Test
    public void wireMockTest () throws IOException {

        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>1111111</response>")));
        System.in.read();
    }

    @Test
    public void easy_wireMockTest () throws IOException, InterruptedException {

        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("1111111")));
        Thread.sleep(10000);
        reset();
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("22222")));
        System.in.read();
    }

    @Test
    public void proxyMockTest () throws IOException {
        stubFor(get(urlMatching(".*")).atPriority(10)
                .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));

        stubFor(get(urlEqualTo("/categories_and_latest")).atPriority(10)
                .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get(WireMockTest.class.getResource("/proxymock.json").getPath().substring(1))))));
        System.in.read();
    }
}
