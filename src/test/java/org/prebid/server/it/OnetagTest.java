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

public class OnetagTest extends IntegrationTest {

    @Test
    public void openrtb2AuctionShouldRespondWithBidsFromOnetag() throws IOException, JSONException {
        // given
        WIRE_MOCK_RULE.stubFor(post(urlPathEqualTo("/onetag-exchange"))
                .withRequestBody(equalToJson(jsonFrom("openrtb2/onetag/test-onetag-bid-request.json")))
                .willReturn(aResponse().withBody(
                        jsonFrom("openrtb2/onetag/test-onetag-bid-response.json"))));

        // when
        final Response response = responseFor("openrtb2/onetag/test-auction-onetag-request.json",
                Endpoint.openrtb2_auction);

        // then
        assertJsonEquals("openrtb2/onetag/test-auction-onetag-response.json", response, singletonList("onetag"));
    }
}
