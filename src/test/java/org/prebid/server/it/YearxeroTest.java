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

public class YearxeroTest extends IntegrationTest {

    @Test
    public void openrtb2AuctionShouldRespondWithBidsFromYearxero() throws IOException, JSONException {
        // given
        WIRE_MOCK_RULE.stubFor(post(urlPathEqualTo("/yearxero-exchange"))
                .withRequestBody(equalToJson(jsonFrom("openrtb2/yearxero/test-yearxero-bid-request.json")))
                .willReturn(aResponse().withBody(jsonFrom("openrtb2/yearxero/test-yearxero-bid-response.json"))));

        // when
        final Response response = responseFor("openrtb2/yearxero/test-auction-yearxero-request.json",
                Endpoint.openrtb2_auction);

        // then
        assertJsonEquals("openrtb2/yearxero/test-auction-yearxero-response.json", response, singletonList("yearxero"));
    }
}
