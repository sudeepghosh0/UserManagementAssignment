package com.UserMnagementProject.UserManagementProject.DTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private UUID id;
    private String name;
    private String email;
    private int age;
    private String username;
    private String country;
    private LocalDateTime createdDate;
}
