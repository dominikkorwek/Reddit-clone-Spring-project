package dodo.reddit.ReqRes;

import lombok.Builder;

import java.util.Date;

@Builder
public record AuthResponse(String jwtToken, String refreshToken, String user,
                           Date expires) {
}
