package org.prebid.server.it;

import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.prebid.server.model.Endpoint;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.util.Collections.singletonList;

public class ZentotemTest extends IntegrationTest {

    @Test
    public void openrtb2AuctionShouldRespondWithBidsFromZentotem() throws IOException, JSONException {
        // given
        WIRE_MOCK_RULE.stubFor(post(urlPathEqualTo("/zentotem-exchange"))
                .withRequestBody(equalToJson(jsonFrom("openrtb2/zentotem/test-zentotem-bid-request.json")))
                .willReturn(aResponse().withBody(jsonFrom("openrtb2/zentotem/test-zentotem-bid-response.json"))));

        // when
        final Response response = responseFor("openrtb2/zentotem/test-auction-zentotem-request.json",
                Endpoint.openrtb2_auction);

        // then
        assertJsonEquals("openrtb2/zentotem/test-auction-zentotem-response.json", response, singletonList("zentotem"));
    }
}
