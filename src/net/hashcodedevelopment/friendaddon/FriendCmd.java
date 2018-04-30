package net.hashcodedevelopment.friendaddon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.hashcodedevelopment.friendaddon.LanguageManager.Language;
import net.hashcodedevelopment.friendaddon.MessageManager.Message;

public class FriendCmd implements CommandExecutor {

	// Receiver | Senders
	public static HashMap<UUID, HashSet<UUID>> invites = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Language language = FriendAddon.language;
			if (args.length == 0) {
				player.sendMessage(MessageManager.getMessage(player, language, Message.F_HELP));
			} else if (args.length == 0) {
				if (args[0].equalsIgnoreCase("list")) {
					player.sendMessage("§8§m----------------");
					player.sendMessage(MessageManager.getMessage(player, language, Message.F_LIST));
					for (String[] data : Utils.getFriends(player.getUniqueId())) {
						player.sendMessage("§8- §e" + data[1]);
					}
					player.sendMessage("§8§m----------------");
				} else if (args[0].equalsIgnoreCase("requests")) {
					player.sendMessage(MessageManager.getMessage(player, language, Message.F_REQUESTS));
				} else
					player.sendMessage(MessageManager.getMessage(player, language, Message.F_HELP));
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("add")) {
					if (Bukkit.getPlayer(args[1]) != null) {
						Player f = Bukkit.getPlayer(args[1]);
						if (!Utils.isFriend(player.getUniqueId(), f.getUniqueId())) {
							if (!f.getUniqueId().equals(player.getUniqueId())) {
								HashSet<UUID> hashSet = new HashSet<>();
								if (invites.containsKey(f.getUniqueId())) {
									hashSet = invites.get(f.getUniqueId());
								}
								if (!invites.containsKey(player.getUniqueId())) {
									hashSet.add(player.getUniqueId());
									invites.put(f.getUniqueId(), hashSet);
									player.sendMessage("§7"+f.getName() + " "
											+ MessageManager.getMessage(player, language, Message.F_ADD_SENT));
									f.sendMessage("§7"+player.getName() + " "
											+ MessageManager.getMessage(f, language, Message.F_ADD_RECEIVED));
								} else
									player.sendMessage(
											MessageManager.getMessage(player, language, Message.F_ALREADY_SENT));
							} else
								player.sendMessage(
										MessageManager.getMessage(player, language, Message.F_ADD_CANT_SEND_2_URSELF));
						} else
							player.sendMessage(MessageManager.getMessage(player, language, Message.F_ALREADY_FRIEND));
					} else
						player.sendMessage(MessageManager.getMessage(player, language, Message.F_PLAYER_IS_NULL));
				} else if (args[0].equalsIgnoreCase("accept")) {
					if (Bukkit.getPlayer(args[1]) != null) {
						Player f = Bukkit.getPlayer(args[1]);
						if (!Utils.isFriend(player.getUniqueId(), f.getUniqueId())) {
							if (!f.getUniqueId().equals(player.getUniqueId())) {
								HashSet<UUID> hashSet = new HashSet<>();
								if (invites.containsKey(player.getUniqueId())) {
									hashSet = invites.get(player.getUniqueId());
									if(hashSet.contains(f.getUniqueId())){
										hashSet.remove(f.getUniqueId());
										invites.put(player.getUniqueId(), hashSet);
										player.sendMessage("§7"+f.getName()+" "+MessageManager.getMessage(player, language, Message.F_ADDED_SENDER));
										player.sendMessage("§7"+player.getName()+" "+MessageManager.getMessage(player, language, Message.F_ADDED_RECEIVER));
									} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_NOT_INVITED));
								} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_NOT_INVITED));
							} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_ADD_CANT_SEND_2_URSELF));
						} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_ALREADY_FRIEND));
					} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_PLAYER_IS_NULL));
				} else if (args[0].equalsIgnoreCase("deny")) {
					if (Bukkit.getPlayer(args[1]) != null) {
						Player f = Bukkit.getPlayer(args[1]);
						if (!Utils.isFriend(player.getUniqueId(), f.getUniqueId())) {
							if (!f.getUniqueId().equals(player.getUniqueId())) {
								HashSet<UUID> hashSet = new HashSet<>();
								if (invites.containsKey(player.getUniqueId())) {
									hashSet = invites.get(player.getUniqueId());
									if(hashSet.contains(f.getUniqueId())){
										hashSet.remove(f.getUniqueId());
										invites.put(player.getUniqueId(), hashSet);
										player.sendMessage("§7"+f.getName()+" "+MessageManager.getMessage(player, language, Message.F_DENYED_SENDER));
										player.sendMessage("§7"+player.getName()+" "+MessageManager.getMessage(player, language, Message.F_DENYED_RECEIVER));
									} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_NOT_INVITED));
								} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_NOT_INVITED));
							} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_ADD_CANT_SEND_2_URSELF));
						} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_ALREADY_FRIEND));
					} else player.sendMessage(MessageManager.getMessage(player, language, Message.F_PLAYER_IS_NULL));
				}
			}
		}
		return false;
	}

}
