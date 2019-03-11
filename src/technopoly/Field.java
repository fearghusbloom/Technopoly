/**
 * 
 */
package technopoly;

import java.util.ArrayList;

/**
 * @author fearg
 *
 */
public class Field {

	//declare the instance variables
	private String name;
	private int onwerID;
	
	/**
	 * default constructor
	 */
	public Field() {

	}

	/**
	 * constructor with all fields as args
	 * @param name
	 * @param onwerID
	 */
	public Field(String name, int onwerID) {
		this.name = name;
		this.setOnwerID(onwerID);;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public int getOnwerID() {
		return onwerID;
	}

	/**
	 * business rule for owner ID - can be between 1 and 4 
	 * @param onwerID
	 */
	public void setOnwerID(int onwerID) throws IllegalArgumentException {
		if (onwerID >= 0 && onwerID <= 4) {
			this.onwerID = onwerID;
		} 
		else {
			throw new IllegalArgumentException("invalid owner ID- must be between 1 and 4");
		}
	}

	/**
	 * checkIfFieldOwned returns true if a player has already invested in the whole field
	 * @return
	 */
	public boolean checkIfFieldOwned() {
		boolean owned = false;
		
		if(this.onwerID != 0) {
			owned = true;
		}
		return owned;
	}
	
	/**
	 * updateFieldsOwnerID checks whether the current field's areas have been invested in by the same 
	 * player. If so, the player's ID is set as the ownerID
	 * @param squares
	 */
	public void updateFieldsOwnerID(ArrayList<Square> squares) {
		ArrayList<Square> thisFieldsAreas = new ArrayList<Square>();
		
		for(Square square : squares) {
			if(square.getField().getName().equals(this.name)) {
				thisFieldsAreas.add(square);
			}
		}
		
		boolean sameOwner = false;
		
		int firstAreasOwner = thisFieldsAreas.get(0).getOwnerID();
		
		for(int i = 1; i < thisFieldsAreas.size(); i++) {
			if(thisFieldsAreas.get(i).getOwnerID() == firstAreasOwner) {
				sameOwner = true;
			}
			else {
				sameOwner = false;
				break;
			}
		}
		
		if(sameOwner == true) {
			this.setOnwerID(thisFieldsAreas.get(0).getOwnerID());
		}
	}
	
}
