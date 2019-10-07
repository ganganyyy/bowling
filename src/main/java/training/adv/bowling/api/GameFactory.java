package training.adv.bowling.api;

public interface GameFactory<T extends Game<? extends Turn, ? extends GameRule<? extends Turn>>> {
	T getGame();
}
