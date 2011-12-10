package shared.game;


public class PlayerInfo {
	private Integer connectionId;
	private String name;
	
	public PlayerInfo(String nameIn) {
		name = nameIn;
	}

	public Integer getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(Integer connectionId) {
		this.connectionId = connectionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
