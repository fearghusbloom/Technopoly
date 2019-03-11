/**
 * 
 */
package technopoly;

/**
 * @author fearg
 *
 */
public class Square {

	// declare the instance variables
	private int squareNumber;
	private String name;

	/**
	 * default constructor
	 */
	public Square() {
	}

	/**
	 * constructor with arguments
	 * 
	 * @param squareNumber
	 * @param name
	 */
	public Square(int squareNumber, String name) {
		this.setSquareNumber(squareNumber);
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public int getSquareNumber() {
		return squareNumber;
	}

	/**
	 * 
	 * @param squareNumber
	 */
	public void setSquareNumber(int squareNumber) throws IllegalArgumentException {
		if (squareNumber > 0 && squareNumber <= 12) {
			this.squareNumber = squareNumber;
		} else {
			throw new IllegalArgumentException("Square number invalid");
		}
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
	 * display all details of a generic square (Compile and Run Time or Free WiFi)
	 */
	public void displayAll() {
		System.out.println("---------------------------------------\n");
		System.out.println("Square Number: " + this.squareNumber);
		System.out.println("Area: " + this.name);
		System.out.println("\n---------------------------------------");
	}

	/**
	 * 
	 * @return
	 */
	public int getAreaCost() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public boolean checkIfOwned(Player player) {
		return false;
	}

	/**
	 * 
	 * @param ownerID
	 */
	public void setOwnerID(int ownerID) {
	}

	/**
	 * 
	 * @return
	 */
	public int getOwnerID() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalDeveloperFee() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public Field getField() {
		Field field = new Field("", 0);
		return field;
	}

	/**
	 * 
	 * @return
	 */
	public int getProjectsInvestedInArea() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isHasProduct() {
		return false;
	}

	/**
	 * 
	 * @param hasProduct
	 */
	public void setHasProduct(boolean hasProduct) {
	}

	/**
	 * 
	 * @param projectsInvestedInArea
	 */
	public void setProjectsInvestedInArea(int projectsInvestedInArea) {
	}

	/**
	 * 
	 * @return
	 */
	public int getProductCost() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public int getProjectCost() {
		return 0;
	}

}
