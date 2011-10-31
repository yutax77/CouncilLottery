package com.yutax77;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.yutax77.Combination.CombinationBuilder;

public class CombinationTest {
	private Person chairman;
	private Person secretary;
	private Combination comb1;
	private Combination comb2;
	private Combination comb3;
	
	@BeforeClass
	public void seupClass() {
		chairman = new Person("hoge");
		secretary = new Person("foo");
		Person snack1 = new Person("bar1");
		Person snack2 = new Person("bar2");
		
		comb1 = new CombinationBuilder()
					.chairman(1.0, chairman)
					.secretary(1.0, secretary)
					.snack(2.0, Pair.of(snack1, snack2))
					.build();
		 
		comb2 = new CombinationBuilder()
					.chairman(1.0, chairman)
					.secretary(1.0, secretary)
					.snack(2.0, Pair.of(snack1, snack2))
					.build();
		
		comb3 = new CombinationBuilder()
					.chairman(2.0, chairman)
					.secretary(1.0, secretary)
					.snack(2.0, Pair.of(snack1, snack2))
					.build();
	}
	
	@Test
	public void testBuilder() {
		CombinationBuilder builder = new CombinationBuilder();
		builder.chairman(1.0, new Person("hoge"))
				.secretary(1.0, new Person("foo"))
				.snack(2.0, Pair.of(new Person("bar1"), new Person("bar2")));
		
		assertNotNull(builder.build());
	}
	
	@Test
	public void testCompareTo() {
		assertTrue(comb1.compareTo(comb2) == 0);
		assertTrue(comb1.compareTo(comb3) > 0);
	}

	@Test
	public void testEquals() {
		assertTrue(comb1.equals(comb2));
		assertFalse(comb1.equals(comb3));
	}
}
