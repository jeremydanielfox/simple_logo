package view;

import model.Receiver;

public class CommandSender {
	private static Receiver myReceiver;
	private static CommandSender instance;

	private CommandSender() {

	}

	protected static CommandSender getInstance() {
		if (instance == null)
			instance = new CommandSender();
		return instance;
	}

	protected static void setReceiver(Receiver receiver) {
		myReceiver = receiver;
	}

	protected static void send(String text) {
		myReceiver.giveText(text);
	}
}
