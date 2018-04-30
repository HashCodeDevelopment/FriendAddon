package net.hashcodedevelopment.friendaddon;

import org.bukkit.entity.Player;

import net.hashcodedevelopment.friendaddon.LanguageManager.Language;

public class MessageManager {

	public enum Message {
		F_HELP, F_LIST, F_REQUESTS, F_ADD_RECEIVED, F_ADD_SENT, F_PLAYER_IS_NULL, F_ALREADY_FRIEND, F_NOT_FRIEND, F_ADD_CANT_SEND_2_URSELF, F_ALREADY_SENT, F_NOT_INVITED, F_ADDED_SENDER, F_ADDED_RECEIVER, F_DENYED_SENDER, F_DENYED_RECEIVER

	}
	
	public static String getMessage(Player player, Language language, Message message){
		String string = "";
		
		switch (language){
		case DE:
			break;
		case EN:
			break;
		default:
			break;
		}
		
		return string;
	}

}
