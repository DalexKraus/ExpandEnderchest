package at.thejano.expandenderchest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    public final String prefix = "§7[§5ExpandEnderchest§7] ";

    private HashMap<UUID, Inventory> inventories = new HashMap();

    @Override
    public void onEnable() {
        System.out.println(prefix + "Plugin geladen!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        System.out.println(prefix + "Plugin deaktiviert!");
    }

    @EventHandler
    public void onOpenChest(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getClickedBlock() != null) {
            if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
                e.setCancelled(true);

                UUID playerId = player.getUniqueId();
                if (inventories.containsKey(playerId)) {
                    Inventory enderChestInventory = inventories.get(playerId);
                    player.openInventory(enderChestInventory);
                }
                else {
                    Inventory inventory = Bukkit.createInventory(null, 9, "§5Enderchest Level 1");
                    inventories.put(playerId, inventory);
                    player.openInventory(inventory);
                }
            }
        }
    }
}
