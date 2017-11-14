package com.lllockkk.security.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by L on 2017/8/21.
 */
public class MockServer {
    public static void main(String[] args) {
        configureFor(8082);
        removeAllMappings();

        stubFor(get(urlPathEqualTo("/order/1")).willReturn(aResponse().withBody("{\"id\":123}").withStatus(200)));
    }
}
