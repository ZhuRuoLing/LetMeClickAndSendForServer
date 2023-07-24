package net.zhuruoling.lmcas.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.logging.LogUtils;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.zhuruoling.lmcas.Command;
import org.slf4j.Logger;

import java.util.List;
import java.util.Random;

import static net.minecraft.server.command.CommandManager.*;

public class SendCommand implements Command {
    public static String command = generateRandomString(5);
    private Logger logger = LogUtils.getLogger();
    private static String generateRandomString(int len) {
        String ch = "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Random random = new Random(System.nanoTime());
            int num = random.nextInt(62);
            stringBuffer.append(ch.charAt(num));
        }
        return stringBuffer.toString();
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal(command).
                requires(ServerCommandSource::isExecutedByPlayer).
                then(argument("text", StringArgumentType.greedyString()).
                        executes(s -> {
                            processCommand(StringArgumentType.getString(s, "text"), s.getSource());
                            return 0;
                        })
                ));
    }

    public void processCommand(String text, ServerCommandSource commandSource) {
        var server = commandSource.getServer();
        server.getPlayerManager().broadcast(Texts.join(List.of(
                Text.of("<"),
                commandSource.getPlayer().getDisplayName(),
                Text.of("> " + text)), Text.of("")), false
        );
        //logger.info("<%s> %s".formatted(commandSource.getPlayer().getGameProfile().getName(), text));
    }
}
