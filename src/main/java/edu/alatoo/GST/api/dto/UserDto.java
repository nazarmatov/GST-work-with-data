package edu.alatoo.GST.api.dto;

import edu.alatoo.GST.data.enumerations.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @Email
    @NotNull
    String email;
    @NotNull
    Role role;
}
