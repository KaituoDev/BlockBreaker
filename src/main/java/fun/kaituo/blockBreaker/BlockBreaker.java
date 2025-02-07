package fun.kaituo.blockBreaker;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockBreaker extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BlockBreakingListener(), this);
        getCommand("getbreaker").setExecutor(new GetBlockBreakerCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        HandlerList.unregisterAll(this);
    }
}