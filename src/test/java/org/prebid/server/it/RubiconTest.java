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

public class RubiconTest extends IntegrationTest {

    @Test
    public void testOpenrtb2AuctionCoreFunctionality() throws IOException, JSONException {
        // given
        WIRE_MOCK_RULE.stubFor(post(urlPathEqualTo("/rubicon-exchange"))
                .withRequestBody(equalToJson(jsonFrom("openrtb2/rubicon/test-rubicon-bid-request.json")))
                .willReturn(aResponse().withBody(jsonFrom("openrtb2/rubicon/test-rubicon-bid-response.json"))));

        // when
        final Response response = responseFor("openrtb2/rubicon/test-auction-rubicon-request.json",
                Endpoint.openrtb2_auction);

        // then
        assertJsonEquals("openrtb2/rubicon/test-auction-rubicon-response.json", response, singletonList("rubicon"));
    }
}
