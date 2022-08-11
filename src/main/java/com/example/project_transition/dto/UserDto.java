package com.example.project_transition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String username;
    private String password;


    public static final class UserDtoBuilder {
        private Long id;
        private String username;

        private UserDtoBuilder() {
        }

        public static UserDtoBuilder anUserDto() {
            return new UserDtoBuilder();
        }

        public UserDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.setId(id);
            userDto.setUsername(username);
            return userDto;
        }
    }
}
