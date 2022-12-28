package es.capgemini.cca.canbemini.security;

import es.capgemini.cca.canbemini.users.UsersDto;

public class LoginReply {
    UsersDto user;
    String token;

    public UsersDto getUser() {
        return user;
    }

    public void setUser(UsersDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
