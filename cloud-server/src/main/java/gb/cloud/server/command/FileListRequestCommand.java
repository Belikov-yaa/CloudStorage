package gb.cloud.server.command;

import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import gb.cloud.server.factory.Factory;
import gb.cloud.service.CommandService;
import io.netty.channel.ChannelHandlerContext;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileListRequestCommand implements CommandService {
    @Override
    public void processCommand(Command command, ChannelHandlerContext ctx) {
        if (command.getArgs().length != 1
                || !Factory.getServerService().isUserAuthenticated(ctx.channel())) {
            ctx.writeAndFlush(new Command(CommandCode.CMD_FAIL,
                    "wrong arguments, expected one directory and user to be logged in"));
            return;
        }


        Path rootUserPath = Factory.getServerService().getUserRootPath(ctx.channel());
        Path requestPath = Paths.get(rootUserPath.toString(), command.getArgs()[0]);

        if (!requestPath.normalize().startsWith(rootUserPath)) return;

        String[] args = PathUtils.lsDirectory(requestPath, rootUserPath);
        if (args.length > 0) {
            ctx.writeAndFlush(new Command(CommandCode.REQUEST_FILE_LIST, args));
        }
    }

    @Override
    public CommandCode getCommandCode() {
        return CommandCode.REQUEST_FILE_LIST;
    }
}
