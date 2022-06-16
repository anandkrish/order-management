package com.order.management.ordermanagement.repository.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;


/**
 * @author Exzion
 */
@Document(collection = "userLogin")
public class UserLogin {

    @Id
    @Indexed
    private String id;

    @Field("userId")
    private String userId;

    @Field("userName")
    private String userName;

    @Field("authToken")
    private String authToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
