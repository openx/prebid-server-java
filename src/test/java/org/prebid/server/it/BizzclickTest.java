package org.prebid.server.it;

import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.prebid.server.model.Endpoint;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.util.Collections.singletonList;

public class BizzclickTest extends IntegrationTest {

    @Test
    public void openrtb2AuctionShouldRespondWithBidsFromBizzclick() throws IOException, JSONException {
        // given
        WIRE_MOCK_RULE.stubFor(post(urlPathEqualTo("/bizzclick-exchange"))
                .withQueryParam("host", equalTo("host"))
                .withQueryParam("source", equalTo("placementId"))
                .withQueryParam("account", equalTo("accountId"))
                .withRequestBody(equalToJson(jsonFrom("openrtb2/bizzclick/test-bizzclick-bid-request.json")))
                .willReturn(aResponse().withBody(jsonFrom("openrtb2/bizzclick/test-bizzclick-bid-response.json"))));

        // when
        final Response response = responseFor("openrtb2/bizzclick/test-auction-bizzclick-request.json",
                Endpoint.openrtb2_auction);

        // then
        assertJsonEquals("openrtb2/bizzclick/test-auction-bizzclick-response.json", response,
                singletonList("bizzclick"));
    }
}
