package com.multi.restapi.section03.valid;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int no;

//        @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @NotNull(message = "아이디는 반드시 입력되어야 합니다.")
    private String id;

    private String pwd;

    @NotNull(message = "이름은 반드시 입력되어야 합니다.")
    @Size(min = 2, message = "이름은 2글자 이상 입력해야 합니다.")
    private String name;

    @Past
    private Date enrollDate;
}

/* @Null // null만 혀용합니다.
 * @NotNull // null을 허용하지 않습니다. "", " "는 허용합니다.
 * @NotEmpty // null, ""을 허용하지 않습니다. " "는 허용합니다.
 * @NotBlank // null, "", " " 모두 허용하지 않습니다.
 *
 * @Email // 이메일 형식을 검사합니다. 다만 ""의 경우를 통과 시킵니다
 * @Pattern(regexp = ) // 정규식을 검사할 때 사용됩니다.
 *
 * @Size(min=, max=) // 길이를 제한할 때 사용됩니다.
 * @Max(value = ) // value 이하의 값을 받을 때 사용됩니다.
 * @Min(value = ) // value 이상의 값을 받을 때 사용됩니다.
 *
 * @Positive // 값을 양수로 제한합니다.
 * @PositiveOrZero // 값을 양수와 0만 가능하도록 제한합니다.
 * @Negative // 값을 음수로 제한합니다.
 * @NegativeOrZero // 값을 음수와 0만 가능하도록 제한합니다.
 *
 * @Future // 현재보다 미래
 * @Past // 현재보다 과거
 *
 * @AssertFalse // false 여부, null은 체크하지 않습니다.
 * @AssertTrue // true 여부, null은 체크하지 않습니다.
 */