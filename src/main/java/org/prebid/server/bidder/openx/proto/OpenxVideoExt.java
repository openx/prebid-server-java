package org.prebid.server.bidder.openx.proto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor(staticName = "of")
@Value
@Builder
public class OpenxVideoExt {

    @JsonProperty("rewarded")
    Integer rewarded;
}
