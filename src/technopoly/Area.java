/**
 * 
 */
package technopoly;

/**
 * @author fearg
 *
 */
public class Area extends Square {

	// business rule constants
	public static final int MIN_PROJECTS = 0;
	public static final int MAX_PROJECTS = 3;
	public static final int MIN_OWNERID = 0;
	public static final int MAX_OWNERID = 4;
	public static final int MIN_COST = 1;

	// instance vars
	private Field field;

	private int areaCost;
	private int projectCost;
	private int productCost;

	private int ownerID;
	private int projectsInvestedInArea;
	private boolean hasProduct;

	private int developerFee;
	private int developerFeeWithOneProject;
	private int developerFeeWithTwoProjects;
	private int developerFeeWithThreeProjects;
	private int developerFeeWithProduct;

	/**
	 * default constructor
	 */
	public Area() {

	}

	
	/**
	 * constructor with all arguments
	 * 
	 * @param squareNumber
	 * @param name
	 * @param field
	 * @param areaCost
	 * @param projectCost
	 * @param productCost
	 * @param ownerID
	 * @param projectsInvestedInArea
	 * @param hasProduct
	 * @param DeveloperFeeFee
	 * @param DeveloperFeeWithOneProject
	 * @param DeveloperFeeWithTwoProjects
	 * @param DeveloperFeeWithThreeProjects
	 * @param DeveloperFeeWithProduct
	 */
	public Area(int squareNumber, String name, Field field, int areaCost, int projectCost, int productCost, int ownerID,
			int projectsInvestedInArea, boolean hasProduct, int DeveloperFee, int DeveloperFeeWithOneProject,
			int DeveloperFeeWithTwoProjects, int DeveloperFeeWithThreeProjects, int DeveloperFeeWithProduct) {
		super(squareNumber, name);
		this.field = field;
		this.setAreaCost(areaCost);
		this.setProjectCost(projectCost);
		this.setProductCost(productCost);
		this.setOwnerID(ownerID);
		this.setProjectsInvestedInArea(projectsInvestedInArea);
		this.setHasProduct(hasProduct);
		this.setDeveloperFee(DeveloperFee);
		this.setDeveloperFeeWithOneProject(DeveloperFeeWithOneProject);
		this.setDeveloperFeeWithTwoProjects(DeveloperFeeWithTwoProjects);
		this.setDeveloperFeeWithThreeProjects(DeveloperFeeWithThreeProjects);
		this.setDeveloperFeeWithProduct(DeveloperFeeWithProduct);
	}

	/**
	 * 
	 */
	public Field getField() {
		return field;
	}

	/**
	 * 
	 * @param field
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * 
	 */
	@Override
	public int getAreaCost() {
		return areaCost;
	}

	/**
	 * 
	 * @param areaCost
	 */
	public void setAreaCost(int areaCost) throws IllegalArgumentException {
		if (areaCost > MIN_COST) {
		this.areaCost = areaCost;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 */
	public int getProjectCost() {
		return projectCost;
	}

	/**
	 * Project cost cannot be less than 0
	 * @param projectCost
	 */
	public void setProjectCost(int projectCost) throws IllegalArgumentException {
		if (projectCost > MIN_COST) {
		this.projectCost = projectCost;
	} else {
		throw new IllegalArgumentException();
	}
	}

	/**
	 * 
	 */
	public int getProductCost() {
		return productCost;
	}

	/**
	 * product cost must be more than 0
	 * @param productCost
	 */
	public void setProductCost(int productCost) throws IllegalArgumentException {
		if (productCost >= MIN_COST) {
		this.productCost = productCost;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 */
	public int getOwnerID() {
		return ownerID;
	}

	/**
	 * owner ID must between 1 and 4 (inclusive)
	 * @param ownerID
	 */
	public void setOwnerID(int ownerID) throws IllegalArgumentException {
		if (ownerID>= MIN_OWNERID && ownerID <= MAX_OWNERID) {
		this.ownerID = ownerID;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 */
	public int getProjectsInvestedInArea() {
		return projectsInvestedInArea;
	}

	/**
	 * projects invested in area must be between 0 and 3 inclusive
	 * @param projectsInvestedInArea
	 * @throws IllegalArgumentException
	 */
	public void setProjectsInvestedInArea(int projectsInvestedInArea) throws IllegalArgumentException {
		if (projectsInvestedInArea >= MIN_PROJECTS && projectsInvestedInArea <= MAX_PROJECTS) {
			this.projectsInvestedInArea = projectsInvestedInArea;
		} else {
			throw new IllegalArgumentException("number of projects not between 0 and 3");
		}

	}

	/**
	 * 
	 */
	public boolean isHasProduct() {
		return hasProduct;
	}

	/**
	 * 
	 */
	public void setHasProduct(boolean hasProduct) {

		this.hasProduct = hasProduct;

	}

	/**
	 * 
	 * @return
	 */
	public int getDeveloperFee() {
		return developerFee;
	}

	/**
	 * developer fee cannot be less than 0
	 * @param DeveloperFeeFee
	 */
	public void setDeveloperFee(int developerFee) throws IllegalArgumentException {
		if (developerFee >= MIN_COST) {
		this.developerFee = developerFee;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getDeveloperFeeWithOneProject() {
		return developerFeeWithOneProject;
	}

	/**
	 * Developer fee with one project cannot be less than 0
	 * @param DeveloperFeeWithOneProject
	 */
	public void setDeveloperFeeWithOneProject(int developerFeeWithOneProject) throws IllegalArgumentException {
		if (developerFeeWithOneProject >= MIN_COST) {
		this.developerFeeWithOneProject = developerFeeWithOneProject;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getDeveloperFeeWithTwoProjects() {
		return developerFeeWithTwoProjects;
	}

	/**
	 * developer fee with two projects must be greater than 0
	 * @param DeveloperFeeWithTwoProjects
	 */
	public void setDeveloperFeeWithTwoProjects(int developerFeeWithTwoProjects) throws IllegalArgumentException {
		if (developerFeeWithTwoProjects >= MIN_COST) {
		this.developerFeeWithTwoProjects = developerFeeWithTwoProjects;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getDeveloperFeeWithThreeProjects() {
		return developerFeeWithThreeProjects;
	}

	/**
	 * 
	 * @param DeveloperFeeWithThreeProjects
	 */
	public void setDeveloperFeeWithThreeProjects(int developerFeeWithThreeProjects) throws IllegalArgumentException {
		if (developerFeeWithThreeProjects >= MIN_COST) {
		this.developerFeeWithThreeProjects = developerFeeWithThreeProjects;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getDeveloperFeeWithProduct() {
		return developerFeeWithProduct;
	}

	/**
	 * 
	 * @param DeveloperFeeWithProduct
	 */
	public void setDeveloperFeeWithProduct(int developerFeeWithProduct) throws IllegalArgumentException {
		if (developerFeeWithProduct >= MIN_COST) {
		this.developerFeeWithProduct = developerFeeWithProduct;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * override square's displayAll method for areas of the board
	 */
	@Override
	public void displayAll() {
		System.out.println("---------------------------------------\n");
		System.out.println("Square Number: " + this.getSquareNumber());
		System.out.println("Field: " + this.field.getName());
		System.out.println("Area: " + this.getName());

		if (this.ownerID == 0) {
			System.out.println("\nOwner: Unowned");
		} else {
			System.out.println("\nOwner: " + this.ownerID);
			System.out.println("Projects developed: " + this.projectsInvestedInArea);
			System.out.println("Product developed: " + this.hasProduct);
		}

		System.out.println("\nArea cost: " + this.areaCost + " developers");
		System.out.println("Project cost: " + this.projectCost + " developers");
		System.out.println("Product Cost: " + this.productCost + " developers");

		System.out.println("\nDeveloperFee fee: " + this.developerFee);
		System.out.println("DeveloperFee with 1 Project: " + this.developerFeeWithOneProject);
		System.out.println("DeveloperFee with 2 Projects: " + this.developerFeeWithTwoProjects);
		System.out.println("DeveloperFee with 3 Projects: " + this.developerFeeWithThreeProjects);
		System.out.println("DeveloperFee with product:" + this.developerFeeWithProduct);
		System.out.println("\n---------------------------------------");
	}

	/**
	 * checkIfOwned returns true if the curDeveloperFee area is invested in by a
	 * player, and returns false if the area has not yet been invested in.
	 * 
	 * @param player
	 * @return
	 */
	public boolean checkIfOwned(Player player) {
		boolean owned = false;

		if (this.ownerID > 0) {
			owned = true;
			if (this.ownerID == player.getPlayerID()) {
				System.out.println("\nYou already invested in this area; it's yours!");
			} else {
				System.out.println("\nThe area already has an investor. Get ready to pay!");
				// call the getCost method & subsequently the subtract and add developers
				// methods
			}
		} else {
			System.out.println("\nThe area is free to invest in.");
		}

		return owned;
	}

	/**
	 * to calculate how much DeveloperFee is owed when an opponent's area is landed
	 * on, the amount owed will depend on the level of development on the area
	 * 
	 * @return
	 */
	public int getTotalDeveloperFee() {
		int totalCost = 0;

		switch (this.projectsInvestedInArea) {
		case 0:
			totalCost = this.developerFee;
			break;
		case 1:
			totalCost = this.developerFeeWithOneProject;
			break;
		case 2:
			totalCost = this.developerFeeWithTwoProjects;
			break;
		case 3:
			if (this.hasProduct == false) {
				totalCost = this.developerFeeWithThreeProjects;
			} else {
				totalCost = this.developerFeeWithProduct;
			}
			break;
		}

		return totalCost;
	}
}
