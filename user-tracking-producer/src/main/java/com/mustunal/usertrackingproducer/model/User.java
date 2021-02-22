package com.mustunal.usertrackingproducer.model;

import com.mustunal.usertrackingproducer.enums.UserId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {

    private UserId userId;

    private String userName;

    private Date dateOfBirth;


}
