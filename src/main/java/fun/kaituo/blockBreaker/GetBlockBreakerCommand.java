package fun.kaituo.blockBreaker;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class GetBlockBreakerCommand implements CommandExecutor {
    private static final String WRONG_COMMAND_USAGE = "§c正确用法: /getbreaker <目标方块名称> <工具耐久值>";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("getbreaker")) {
            if (!sender.isOp()) {
                sender.sendMessage("§c你没有权限使用此指令！");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§c只有玩家才能使用此指令！");
                return true;
            }
            Player player = (Player) sender;
            if (args.length != 2) {
                player.sendMessage(WRONG_COMMAND_USAGE);
                return true;
            }

            int durability;
            try {
                durability = Integer.parseInt(args[1]);
            } catch (Exception e) {
                sender.sendMessage(WRONG_COMMAND_USAGE);
                return true;
            }
            String type = args[0];

            ItemStack item;
            ItemMeta meta;
            NBTItem nbtItem;
            ItemStack finalItem;
            switch (type) {
                case "bedrock":
                    item = new ItemStack(Material.PRISMARINE_SHARD);
                    meta = item.getItemMeta();

                    meta.setDisplayName("§f基岩钻头");
                    meta.setLore(Arrays.asList(
                            "§f耐久值：" + durability,
                            "",
                            "§6§o由矮人族锻造的坚固钻头，能破坏世界上最难以破坏的岩石：基岩！"
                    ));
                    meta.addEnchant(Enchantment.EFFICIENCY, 10, true);
                    item.setItemMeta(meta);

                    nbtItem = new NBTItem(item);
                    nbtItem.setInteger("Durability", durability);
                    nbtItem.setInteger("BasicDurability", durability);
                    finalItem = nbtItem.getItem();
                    player.getInventory().addItem(finalItem);
                    return false;

                case "glass":
                    item = new ItemStack(Material.FLINT);
                    meta = item.getItemMeta();

                    meta.setDisplayName("§f钻石刀");
                    meta.setLore(Arrays.asList(
                            "§f耐久值：" + durability,
                            "",
                            "§b§o由工业级金刚石磨制而成，可用于快速切割玻璃！"
                    ));
                    meta.addEnchant(Enchantment.EFFICIENCY, 10, true);
                    meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                    item.setItemMeta(meta);

                    nbtItem = new NBTItem(item);
                    nbtItem.setInteger("Durability", durability);
                    nbtItem.setInteger("BasicDurability", durability);
                    finalItem = nbtItem.getItem();
                    player.getInventory().addItem(finalItem);
                    return false;
            }
            player.sendMessage("§c尚不支持的目标方块类型！目前支持以下方块：");
            player.sendMessage("§6玻璃(不区分颜色和玻璃板)(glass)");
            player.sendMessage("§6基岩(bedrock)");
            return true;
        }
        return false;
    }
}
