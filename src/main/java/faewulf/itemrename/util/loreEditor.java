package faewulf.itemrename.util;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class loreEditor {
    public static void setLore(ItemStack stack, int lineIndex, Text loreText) {
        List<Text> lore = stack.getTooltip((PlayerEntity) stack.getHolder(), TooltipContext.ADVANCED);

        //should fill empty line with null?
        int currentLoreSize = lore.size();
        while (lineIndex > currentLoreSize) {
            currentLoreSize++;
            lore.add(Text.of(" "));
        }

        //replace target lore into this item
        lore.set(lineIndex - 1, loreText);

        //TODO: replace item?
    }

    public static void insertLore(ItemStack stack, int lineIndex, Text loreText) {
        List<Text> lore = stack.getTooltip((PlayerEntity) stack.getHolder(), TooltipContext.ADVANCED);

        //if lineindex > lines
        //should fill empty line with null?
        int currentLoreSize = lore.size();
        while (lineIndex - 1 > currentLoreSize) {
            currentLoreSize++;
            lore.add(Text.of(" "));
        }

        //replace target lore into this item
        lore.add(lineIndex - 1, loreText);

        //TODO: replace item?
    }

    public static void addLore(ItemStack stack, Text loreText) {
        List<Text> lore = stack.getTooltip((PlayerEntity) stack.getHolder(), TooltipContext.ADVANCED);

        //replace target lore into this item
        lore.add(loreText);

        //TODO: replace item?
    }

    public static void removeLore(ItemStack stack) {
        //just replace the obj

        List<Text> lore = stack.getTooltip((PlayerEntity) stack.getHolder(), TooltipContext.ADVANCED);

        lore.clear();

        //TODO: replace item?
    }

    public static void removeLoreLine(ItemStack stack, int index) {
        List<Text> lore = stack.getTooltip((PlayerEntity) stack.getHolder(), TooltipContext.ADVANCED);

        //replace target lore into this item
        if (index <= lore.size())
            lore.remove(index - 1);

        //TODO: replace item?
    }
}
