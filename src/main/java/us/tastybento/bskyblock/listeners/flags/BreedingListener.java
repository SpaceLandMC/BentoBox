/**
 * 
 */
package us.tastybento.bskyblock.listeners.flags;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import us.tastybento.bskyblock.BSkyBlock;
import us.tastybento.bskyblock.lists.Flags;

/**
 * Handles breeding protection
 * Note - animal protection is done elsewhere.
 * @author tastybento
 *
 */
public class BreedingListener extends AbstractFlagListener {

    public BreedingListener(BSkyBlock plugin) {
        super(plugin);
    }


    /**
     * A list of items that cause breeding if a player has them in their hand and they click an animal
     * This list may need to be extended with future versions of Minecraft.
     */
    private final static List<Material> BREEDING_ITEMS = Arrays.asList(
            Material.EGG,
            Material.WHEAT,
            Material.CARROT_ITEM,
            Material.SEEDS);


    @EventHandler(priority = EventPriority.LOW, ignoreCancelled=true)
    public void onPlayerInteract(final PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() != null && e.getRightClicked() instanceof Animals) {
            ItemStack inHand = e.getPlayer().getInventory().getItemInMainHand();
            if (e.getHand().equals(EquipmentSlot.OFF_HAND)) {
                inHand = e.getPlayer().getInventory().getItemInOffHand();
            }
            if (inHand != null && BREEDING_ITEMS.contains(inHand.getType())) {
                checkIsland(e, e.getRightClicked().getLocation(), Flags.BREEDING);
            }
        }
    }

}
