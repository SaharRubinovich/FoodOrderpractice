package com.jb.foodOrderPractice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private long id;
    private String userEmail;
    private String userName;
    private String userPass;
    private String userType;
}
