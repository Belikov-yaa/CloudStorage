package gb.cloud.server.command;

import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import gb.cloud.server.factory.Factory;
import gb.cloud.service.CommandService;
import gb.cloud.utils.PropertiesLoader;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthenticateUserCommand implements CommandService {

    @Override
    public void processCommand(Command command, ChannelHandlerContext ctx) {

        if (command.getArgs() == null || command.getArgs().length != 2) {
            ctx.writeAndFlush(new Command(CommandCode.CMD_FAIL, "Command run with illegal arguments, expected login and password"));
            ctx.close();
            return;
        }

        String login = command.getArgs()[0];
        String password = command.getArgs()[1];

        if (Factory.getDB().getUser(login, password)) {
            Factory.getServerService().subscribeUser(login, ctx.channel());
            ctx.writeAndFlush(new Command(CommandCode.CMD_SUCCESS, "User successfully authenticated"));

            try {
                Files.createDirectories(Paths.get(PropertiesLoader.getProperty("server.files.root.path"), login));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            ctx.writeAndFlush(new Command(CommandCode.CMD_FAIL, "User authentication fail"));
            ctx.close();
        }
    }

    @Override
    public CommandCode getCommandCode() {
        return CommandCode.AUTHENTICATE;
    }

}
