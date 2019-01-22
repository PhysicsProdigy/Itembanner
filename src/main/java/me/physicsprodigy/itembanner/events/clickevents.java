package me.physicsprodigy.itembanner.events;

import me.physicsprodigy.itembanner.Itembanner;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class clickevents implements Listener {

    Itembanner plugin;

    public clickevents(Itembanner plugin) {
        this.plugin = plugin;
    }

    @EventHandler

    public void clickEvent(InventoryClickEvent e){

        Player p = (Player)e.getWhoClicked();
        final String Blacklist_menu = ChatColor.RED +"Banned items";
        final String Blacklist_menu2 = ChatColor.RED +"Banned items 2";
        //Menus

        if (e.getCurrentItem() !=null && e.getInventory().getName() != null && e.getClickedInventory().getTitle().equalsIgnoreCase(Blacklist_menu)){
            //tests if the window is the main menu to prevent any user clicks from working
            if (e.getCurrentItem()!=null && e.getCurrentItem().getItemMeta()!=null){
            //if they select the one of the options, then something happens...
                switch (e.getCurrentItem().getItemMeta().getDisplayName()){

                    case "Border":
                        e.setCancelled(true);
                        break;
                    case "Go to the next page":
                        plugin.openBlacklistmenu2(p);
                        break;
                    case "How to use this menu.":
                        e.setCancelled(true);
                        break;
                }
            }
        }
        if (e.getCurrentItem() != null && e.getInventory().getName() != null && e.getClickedInventory().getTitle().equalsIgnoreCase(Blacklist_menu2) && e.getCurrentItem().getType() != null) {
            //tests if the window is the main menu to prevent any user clicks from working
            if (e.getCurrentItem()!=null && e.getCurrentItem().getItemMeta()!=null) {
                //if they select the one of the options, then something happens...
                switch (e.getCurrentItem().getItemMeta().getDisplayName()) {

                    case "Border":
                        e.setCancelled(true);
                        break;
                    case "Go to the previous page":
                        e.setCancelled(true);
                        plugin.openBlacklistmenu(p);
                        break;
                }
            }
        }
    }
    @EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent e){
        final String Blacklist_menu = ChatColor.RED +"Banned items";
        final String Blacklist_menu2 = ChatColor.RED +"Banned items 2";
        if (e.getInventory().getName().equalsIgnoreCase(Blacklist_menu)||e.getInventory().getName().equalsIgnoreCase(Blacklist_menu2)){
            plugin.closing(e);
        }else{plugin.RemoveOnOpenInv(e);}
    }
    @EventHandler
    public void DisablingitemsEventsonpickup(PlayerPickupItemEvent e){
        plugin.Banonpickup(e);
    }

    @EventHandler
    public void invclicktest(InventoryClickEvent e){
        final String Blacklist_menu = ChatColor.RED +"Banned items";
        final String Blacklist_menu2 = ChatColor.RED +"Banned items 2";
        if(e.getCurrentItem() != null && e.getCurrentItem().getType() != null && !e.getCurrentItem().getType().equals(Material.AIR)){
            if (!e.getView().getTopInventory().getName().equalsIgnoreCase(Blacklist_menu)&&!e.getView().getTopInventory().getName().equalsIgnoreCase(Blacklist_menu2)){
                plugin.inventoryclicktest(e);
            }
        }
    }
}
