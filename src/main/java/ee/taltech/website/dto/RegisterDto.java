package ee.taltech.website.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {

    private String username;
    private String email;
    private String password;
}

