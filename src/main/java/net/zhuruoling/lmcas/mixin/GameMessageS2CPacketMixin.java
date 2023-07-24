package net.zhuruoling.lmcas.mixin;

import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.zhuruoling.lmcas.command.SendCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GameMessageS2CPacket.class)
public abstract class GameMessageS2CPacketMixin {
    //does not work
    @ModifyVariable(method = "<init>(Lnet/minecraft/text/Text;Z)V", at = @At("HEAD"), index = 1, name = "text", argsOnly = true)
    private static Text modifyVariable(Text value) {
        return modifyText(value, 114514);
    }

    @Unique
    private static Text modifyText(Text text, int maxDepth) {
        var t = text.copy();
        var s = text.getStyle();
        int currentDepth = maxDepth - 1;
        if (currentDepth < 0) return text;
        if (s != null && s.getClickEvent() != null) {
            if (s.getClickEvent().getAction() == ClickEvent.Action.RUN_COMMAND /*&& !s.getClickEvent().getValue().startsWith("/")*/) {
                var val = "/" + SendCommand.command + " " + s.getClickEvent().getValue();
                s = s.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, val));
            }
        }
        var w = t.copyContentOnly().setStyle(s);
        text.getSiblings().stream().map(it -> modifyText(it, currentDepth)).forEach(w::append);
        return w;
    }

}