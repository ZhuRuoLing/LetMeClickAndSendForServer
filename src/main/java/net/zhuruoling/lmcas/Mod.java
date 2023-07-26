package net.zhuruoling.lmcas;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.zhuruoling.lmcas.command.SendCommand;

public class Mod implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
    }

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        new SendCommand().register(dispatcher, registryAccess, environment);
    }
}
