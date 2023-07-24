package net.zhuruoling.lmcas;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.zhuruoling.lmcas.command.SendCommand;

public class Mod implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        CommandRegistrationCallback.EVENT.register(new SendCommand()::register);
    }
}