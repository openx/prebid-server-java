package org.prebid.server.bidder.openx.proto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Value;
import org.prebid.server.proto.openrtb.ext.request.ExtImpAuctionEnvironment;

import java.util.Map;

/**
 * Defines bidrequest.imp.ext for outgoing requests from OpenX adapter
 */
@Builder(toBuilder = true)
@Value
public class OpenxImpExt {

    @JsonProperty("customParams")
    Map<String, JsonNode> customParams;

    @JsonProperty("ae")
    @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
    ExtImpAuctionEnvironment auctionEnvironment;

    String gpid;

    JsonNode skadn;

    JsonNode data;
}
