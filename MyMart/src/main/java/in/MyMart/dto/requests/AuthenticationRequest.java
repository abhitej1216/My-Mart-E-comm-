package in.MyMart.dto.requests;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    private String userName;
    private String password;

}
