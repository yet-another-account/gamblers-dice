package eukaryote.dice;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

public class TestGamblerDie {
	@Test
	public void testUniform() {
		Die gamblersDie = new GamblerDie();
		int[] result = new int[6];

		// roll 10 million times
		// 10x better than the original test
		final int iters = 10_000_000;
		for (int i = 0; i < iters; i++) {
			result[gamblersDie.roll() - 1]++;
		}

		final int avg = iters / 6;

		for (int i = 0; i < 6; i++) {
			Assert.assertEquals("Not uniform enough!", 0D, (result[i] - avg) / iters, 0.001);
		}
	}
	
	@Test
	public void testActuallyNotUniform() throws Exception {
		GamblerDie gamblersDie = new GamblerDie();
		int[] state = {0, 0, 1, 0, 0, 0};
		
		// reflection voodoo
		Field f = gamblersDie.getClass().getDeclaredField("state");
		f.setAccessible(true);
		f.set(gamblersDie, state);
		
		// make sure the dice is a lie
		Assert.assertEquals("Not not uniform enough!", 3, gamblersDie.roll());
	}
}
