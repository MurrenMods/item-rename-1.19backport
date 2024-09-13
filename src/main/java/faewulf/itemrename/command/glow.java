package faewulf.itemrename.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import faewulf.itemrename.util.ownerCheck;
import faewulf.itemrename.util.permission;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

public class glow {
    static public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("glow")
                        .requires(ServerCommandSource::isExecutedByPlayer)
                        .requires(Permissions.require(permission.GLOW))
                        .executes(glow::run)
        );
    }

    static private int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        ServerPlayerEntity player = context.getSource().getPlayerOrThrow();

        //get holding item
        ItemStack holding = player.getStackInHand(Hand.MAIN_HAND);

        //if not holding anything
        if (holding.isEmpty()) {
            throw new SimpleCommandExceptionType(
                    Text.of("You must hold an item to modify it.")).create();
        }

        ownerCheck.check(player, holding);

        if (holding.hasGlint()) {
            player.sendMessage(Text.of("Item already has enchantment glow."), false);
            return 0;
        }

        holding.getOrCreateNbt().putBoolean("ForceGlint", true);

        return 0;
    }
}
