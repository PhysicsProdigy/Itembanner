package me.physicsprodigy.itembanner.commands;

import me.physicsprodigy.itembanner.Itembanner;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class banneditems implements CommandExecutor {

    Itembanner plugin;

    public banneditems(Itembanner plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if (sender instanceof Player){
            Player p = (Player)sender;
            if (p.hasPermission("banneditemscommand"))
            plugin.openBlacklistmenu(p);
        }
        else {Player p = (Player)sender; p.sendMessage("You do not have the required permissions to issue this command.");}


        return true;
    }
}
