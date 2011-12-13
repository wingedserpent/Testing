package shared.game;

/**
 * Stores all current state information for a player.
 */
public class PlayerState {
	private Integer connectionId;
	private String name;
	private Float xPos, yPos;

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
	
	@Override
	public String toString() {
		return "[connectionId: " + connectionId + ", name: " + name + "]";
	}

	public Float getxPos() {
		return xPos;
	}

	public void setXPos(Float xPos) {
		this.xPos = xPos;
	}

	public Float getYPos() {
		return yPos;
	}

	public void setyPos(Float yPos) {
		this.yPos = yPos;
	}
}
