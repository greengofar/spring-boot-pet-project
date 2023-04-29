package com.andev.dto;

import com.andev.validation.UserInfo;
import com.andev.validation.group.CreateAction;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {
    @Email
    String userName;

    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    @Size(min = 3, max = 128)
    String firstName;

    @Size(min = 3, max = 128)
    String lastName;

    @Email
    String email;

    @NotNull
    String phone;

    MultipartFile image;
}
