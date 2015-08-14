package ba.bitcamp.texas.run;

import java.util.ArrayList;

public class Player {

	private String name;
	private Integer positionAtTable;
	private ArrayList<Card> hand;
	private int chips;
	private boolean active;
	private boolean folded;
	private String host;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	public Player(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the positionAtTable
	 */
	public Integer getPositionAtTable() {
		return positionAtTable;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @param positionAtTable the positionAtTable to set
	 */
	public void setPositionAtTable(Integer positionAtTable) {
		this.positionAtTable = positionAtTable;
	}
	
	
	
	
}
