package me.physicsprodigy.itembanner;

import javafx.scene.layout.BackgroundImage;
import jdk.nashorn.internal.ir.IfNode;
import me.physicsprodigy.itembanner.commands.banneditems;
import me.physicsprodigy.itembanner.events.clickevents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Itembanner extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("banneditems").setExecutor(new banneditems(this));

        getServer().getPluginManager().registerEvents(new clickevents(this),this);

    }

    public void openBlacklistmenu(Player p){

        Inventory Blacklist_menu = Bukkit.createInventory(p,54, ChatColor.RED +"Banned items");

        ItemStack Border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack Info = new ItemStack(Material.PAPER);
        ItemStack Next = new ItemStack(Material.SIGN);

        ItemMeta Border_Meta = Border.getItemMeta();
        Border_Meta.setDisplayName("Border");
        Border.setItemMeta(Border_Meta);

        ItemMeta Info_meta = Info.getItemMeta();
        Info_meta.setDisplayName(ChatColor.WHITE + "How to use this menu.");
        ArrayList<String> Info_lore = new ArrayList<>();
        Info_lore.add(ChatColor.GRAY + "Just chuck items that require banning in this menu.");
        Info_meta.setLore(Info_lore);
        Info.setItemMeta(Info_meta);

        ItemMeta Next_meta = Next.getItemMeta();
        Next_meta.setDisplayName(ChatColor.WHITE + "Go to the next page");
        Next.setItemMeta(Next_meta);

        File file = new File("plugins//BannedItems//"+p.getWorld().getName()+".yml");

        if (file.exists()){
            YamlConfiguration inventory = YamlConfiguration.loadConfiguration(file);
            ItemStack[] contents = Blacklist_menu.getContents();
            List<?> list = inventory.getList("Inventory");

            for (int i=0; i<list.size(); i++){
                contents[i] = (ItemStack) list.get(i);
            }
            Blacklist_menu.setContents(contents);
        }

        Blacklist_menu.setItem(36, Border);
        Blacklist_menu.setItem(37, Border);
        Blacklist_menu.setItem(38, Border);
        Blacklist_menu.setItem(39, Border);
        Blacklist_menu.setItem(40, Border);
        Blacklist_menu.setItem(41, Border);
        Blacklist_menu.setItem(42, Border);
        Blacklist_menu.setItem(43, Border);
        Blacklist_menu.setItem(44, Border);
        Blacklist_menu.setItem(45, Info);
        Blacklist_menu.setItem(46, Border);
        Blacklist_menu.setItem(47, Border);
        Blacklist_menu.setItem(48, Border);
        Blacklist_menu.setItem(49, Border);
        Blacklist_menu.setItem(50, Border);
        Blacklist_menu.setItem(51, Border);
        Blacklist_menu.setItem(52, Border);
        Blacklist_menu.setItem(53, Next);

        p.openInventory(Blacklist_menu);

    }

    public void openBlacklistmenu2(Player p){
        Inventory Blacklist_menu2 = Bukkit.createInventory(p,54, ChatColor.RED +"Banned items 2");

        ItemStack Border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack Back = new ItemStack(Material.SIGN);
        ItemStack Next = new ItemStack(Material.SIGN);

        ItemMeta Border_Meta = Border.getItemMeta();
        Border_Meta.setDisplayName("Border");
        Border.setItemMeta(Border_Meta);

        ItemMeta Back_meta = Back.getItemMeta();
        Back_meta.setDisplayName(ChatColor.WHITE + "Go to the previous page");
        Back.setItemMeta(Back_meta);

        ItemMeta Next_meta = Next.getItemMeta();
        Next_meta.setDisplayName(ChatColor.WHITE + "Go to the next page");
        Next.setItemMeta(Next_meta);

        File file = new File("plugins//BannedItems//"+p.getWorld().getName()+".yml");

        if (file.exists()){
            YamlConfiguration inventory = YamlConfiguration.loadConfiguration(file);
            ItemStack[] contents = Blacklist_menu2.getContents();
            List<?> list2 = inventory.getList("Inventory2");

            for (int i=0;i<list2.size(); i++){
                contents[i] = (ItemStack) list2.get(i);
            }
            Blacklist_menu2.setContents(contents);
        }

        Blacklist_menu2.setItem(36, Border);
        Blacklist_menu2.setItem(37, Border);
        Blacklist_menu2.setItem(38, Border);
        Blacklist_menu2.setItem(39, Border);
        Blacklist_menu2.setItem(40, Border);
        Blacklist_menu2.setItem(41, Border);
        Blacklist_menu2.setItem(42, Border);
        Blacklist_menu2.setItem(43, Border);
        Blacklist_menu2.setItem(44, Border);
        Blacklist_menu2.setItem(45, Back);
        Blacklist_menu2.setItem(46, Border);
        Blacklist_menu2.setItem(47, Border);
        Blacklist_menu2.setItem(48, Border);
        Blacklist_menu2.setItem(49, Border);
        Blacklist_menu2.setItem(50, Border);
        Blacklist_menu2.setItem(51, Border);
        Blacklist_menu2.setItem(52, Border);
        Blacklist_menu2.setItem(53, Border);

        p.openInventory(Blacklist_menu2);
    }

    public void closing(InventoryCloseEvent e){
        final String Blacklist_menu = ChatColor.RED +"Banned items";
        final String Blacklist_menu2 = ChatColor.RED +"Banned items 2";
        if (e.getInventory().getTitle().equalsIgnoreCase(Blacklist_menu)){
            File file = new File("plugins//BannedItems//"+e.getPlayer().getWorld().getName()+".yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    } catch (IOException e1) {}
                YamlConfiguration inventory = YamlConfiguration.loadConfiguration(file);
                ArrayList<ItemStack> list =  new ArrayList<>();
                ArrayList<ItemStack> list2 = new ArrayList<>();
                inventory.set("Inventory", list);
                inventory.set("Inventory2", list2);
                try {
                    inventory.save(file);
                } catch (IOException e1) {}
            }
            YamlConfiguration inventory = YamlConfiguration.loadConfiguration(file);
            ItemStack[] contents = e.getInventory().getContents();
            ArrayList<ItemStack> list =  new ArrayList<>();
            for (int i = 0; i < 36; i++) {
                ItemStack item = contents[i];
                if (!(item == null)) {
                    item.setAmount(1);
                    list.add(item);
                }
                inventory.set("Inventory", list);
            }
            try {
                inventory.save(file);
            } catch (IOException e1) {}

        }
        if (e.getInventory().getTitle().equalsIgnoreCase(Blacklist_menu2)){
            File file = new File("plugins//BannedItems//"+e.getPlayer().getWorld().getName()+".yml");
            YamlConfiguration inventory = YamlConfiguration.loadConfiguration(file);
            ItemStack[] contents = e.getInventory().getContents();
            ArrayList<ItemStack> list2 = new ArrayList<>();
            for (int i = 0; i < 36; i++) {
                ItemStack item = contents[i];
                if (!(item == null)) {
                    item.setAmount(1);
                    list2.add(item);
                }
                inventory.set("Inventory2", list2);
            }
            try {
                inventory.save(file);
            } catch (IOException e1) {}
        }
    }

    public void Banonpickup(PlayerPickupItemEvent e){
        File file = new File("plugins//BannedItems//"+e.getPlayer().getWorld().getName()+".yml");
        Player p = e.getPlayer();
        if(!p.hasPermission("itemsbeingbanned")) {
            if (file.exists()) {
                YamlConfiguration inventory = YamlConfiguration.loadConfiguration(file);
                List<?> list = inventory.getList("Inventory");
                List<?> list2 = inventory.getList("Inventory2");
                ItemStack item = e.getItem().getItemStack();
                int before = item.getAmount();
                item.setAmount(1);
                if (list.contains(e.getItem().getItemStack()) || list2.contains(e.getItem().getItemStack())) {
                    e.setCancelled(true);
                    p.sendMessage("Sorry, this item has been banned by the admins");
                    e.getItem().getItemStack().setAmount(0);
                } else {
                    item.setAmount(before);
                }
            }
        }
    }

    public void RemoveOnOpenInv(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if(!p.hasPermission("itemsbeingbanned")) {
            ItemStack[] contents = p.getInventory().getContents();
            for (int i = 0; i < p.getInventory().getSize(); i++) {
                ItemStack item = contents[i];
                if (item != null) {
                    int original = item.getAmount();
                    item.setAmount(1);
                    File file = new File("plugins//BannedItems//" + e.getPlayer().getWorld().getName() + ".yml");
                    if (file.exists()) {
                        YamlConfiguration inventory = YamlConfiguration.loadConfiguration(file);
                        List<?> list = inventory.getList("Inventory");
                        List<?> list2 = inventory.getList("Inventory2");
                        if (!list.isEmpty() || !list2.isEmpty()) {
                            if (list.contains(item) || list2.contains(item)) {
                                p.sendMessage("It has been detected that you had some " + item.getType() + " in your inventory. This item has been banned by the admin and so... has been removed.");
                                item.setAmount(0);
                            } else {
                                item.setAmount(original);
                            }
                        } else {
                            item.setAmount(original);
                        }
                    } else {
                        item.setAmount(original);
                    }
                }
            }
        }
    }

    public void inventoryclicktest(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(!p.hasPermission("itemsbeingbanned")) {
            ItemStack Item = e.getCurrentItem();
            int original = Item.getAmount();
            Item.setAmount(1);
            File file = new File("plugins//BannedItems//" + e.getWhoClicked().getWorld().getName() + ".yml");
            if (Item != null) {
                if (file.exists()) {
                    YamlConfiguration inventory = YamlConfiguration.loadConfiguration(file);
                    List<?> list = inventory.getList("Inventory");
                    List<?> list2 = inventory.getList("Inventory2");
                    if (!list.isEmpty() || !list2.isEmpty()) {
                        if (list.contains(Item) || list2.contains(Item)) {
                            e.setCancelled(true);
                            p.sendMessage("This item has been banned by the admins and has been removed as you clicked it.");
                            Item.setAmount(0);
                        } else {
                            Item.setAmount(original);
                        }
                    } else {
                        Item.setAmount(original);
                    }
                } else {
                    Item.setAmount(original);
                }
            } else {
                Item.setAmount(original);
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
