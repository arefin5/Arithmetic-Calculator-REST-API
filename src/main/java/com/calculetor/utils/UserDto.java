package com.calculetor.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id; // Ensure this is Long
    private String email;
        private String status = "active"; // Default value

}
