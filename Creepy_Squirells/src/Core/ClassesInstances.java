package Core;

import org.newdawn.slick.state.GameState;

import Connection.ClientTCP;
import Connection.ServerTCP;

import States.CreditsState;
import States.MenuState;
import States.ServerClientState;
import States.Shooting;

public class ClassesInstances {
	public static CreditsState creditsState;
	public static GameState serverGameState;
	public static GameState clientGameState;
	public static MenuState menuState;
	public static ServerClientState serverClientState;

	public static ServerTCP serverTCP;
	public static ClientTCP clientTCP;
	



}
