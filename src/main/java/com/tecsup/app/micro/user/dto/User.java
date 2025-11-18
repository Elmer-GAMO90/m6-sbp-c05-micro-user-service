package com.tecsup.app.micro.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//Para las pruebas unitarias
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    //private String address;
}
