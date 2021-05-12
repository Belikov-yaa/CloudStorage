package gb.cloud.service.impl;

import gb.cloud.domain.Command;
import gb.cloud.domain.CommandCode;
import gb.cloud.service.CommandDictionaryService;
import gb.cloud.service.CommandService;
import io.netty.channel.ChannelHandlerContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandDictionaryServiceImpl implements CommandDictionaryService {

    private final Map<CommandCode, CommandService> commandDictionary;

    public CommandDictionaryServiceImpl(Set<CommandService> commandServices) {

        Map<CommandCode, CommandService> commandDictionary = new HashMap<>();
        for (CommandService commandService : commandServices) {
            commandDictionary.put(commandService.getCommandCode(), commandService);
        }

        this.commandDictionary = Collections.unmodifiableMap(commandDictionary);
    }

    @Override
    public void processCommand(Command command, ChannelHandlerContext ctx) {
        CommandService c = commandDictionary.get(command.getCode());
        if (c == null) {
            System.out.printf("Command %s not found", command.getCode());
            return;
        }
        c.processCommand(command, ctx);
    }

    @Override
    public CommandService getCommandService(CommandCode code) {
        return commandDictionary.get(code);
    }
}
