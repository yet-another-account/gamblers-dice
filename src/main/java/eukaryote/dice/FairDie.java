package eukaryote.dice;

import java.util.Random;

public class FairDie implements Die {
	int nsides;
	Random rnd;
	
	public FairDie() {
		this(6);
	}
	
	public FairDie(int nsides) {
		this.nsides = nsides;
		rnd = new Random();
	}

	@Override
	public int roll() {
		return rnd.nextInt(nsides) + 1;
	}
	
	
}
