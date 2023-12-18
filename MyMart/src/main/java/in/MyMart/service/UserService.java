package in.MyMart.service;

import in.MyMart.dto.requests.NewUserRequest;

public interface UserService {
    public String createNewAccount(NewUserRequest newUserRequest);
    public String updateUserRecord(NewUserRequest newUserRequest, int userId);
}
