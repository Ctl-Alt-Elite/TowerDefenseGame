package firstday;

public class proteinPowder {
	
	private int amount;
	private boolean consumed;
	
	//Constructors - One default, another allowing people to choose amount.
	proteinPowder() {
		amount = 0;
		consumed = false;
	}
	proteinPowder(int x){
		amount = x;
		consumed = false;
	}
	
	//Consumes all available protein powder.
	public void consume() {
		if (amount==0) {
			JavaTutorial1.print("Protein powder consumed.");
			this.consumed = true;
			this.amount = 0;
		}
		else if (amount>0) {JavaTutorial1.print("You must first gain more powder.");}
		else {JavaTutorial1.print("A singularity seems to have consumed everything.");}
	}
	//Refills the protein powder by a given amount.
	public void refill(int x) {
		if (this.amount==0) {
			this.amount=x;
			this.consumed=false;
		}
		else if (this.amount>0) {JavaTutorial1.print("The protein powder has not been consumed.");}
		else {JavaTutorial1.print("You have created a singularity and destroyed the universe.");}
	}
	
	//Check consumption status of protein powder.
	public boolean isConsumed() {
		return this.consumed;
	}
	
}
