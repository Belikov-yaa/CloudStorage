package gb.cloud.domain;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Класс комманд обмена с сервером + аргументы
 */

public class Command implements Serializable {

    private final CommandCode code;
    private String[] args;

    public Command(CommandCode code, String... args) {
        this.code = code;
        this.args = args;
    }

    public CommandCode getCode() {
        return code;
    }

    public String[] getArgs() {
        return args;
    }

    public Command setArgs(String... args) {
        this.args = args;
        return this;
    }

    @Override
    public String toString() {
        return code.toString() + " : " + new String(Arrays.toString(args).getBytes(StandardCharsets.UTF_8));
    }
}
