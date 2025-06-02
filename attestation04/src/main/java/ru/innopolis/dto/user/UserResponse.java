package ru.innopolis.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;


/**
 * Слой DTО
 *
 * <p>Класс содержит получаемые параметры записи Пользователь</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Исходящие данные Пользователь")
public class UserResponse implements Serializable {

    /**
     * Имя пользователя
     */
    @Schema(description = "Имя пользователя")
    private String username;

    /**
     * Пароль
     */
    @Schema(description = "Пароль")
    private String password;

    /**
     * Действующая/отключенная учетная запись
     */
    @Schema(description = "Действующая/отключенная учетная запись")
    private Boolean enabled;

    /**
     * Список полномочий ролей
     */
    @Schema(description = "Список полномочий ролей")
    private List<String> authorities;
}
