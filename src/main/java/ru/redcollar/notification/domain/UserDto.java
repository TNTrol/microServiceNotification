package ru.redcollar.notification.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty("preferred_username")
    private String login;

    @JsonProperty("role")
    private List<RoleDto> roles = new ArrayList<>();
}
