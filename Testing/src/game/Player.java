package game;

public class Player implements IPlayer {
	private Long id;
	private String name;
	
	public Player(String nameIn) {
		name = nameIn;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
