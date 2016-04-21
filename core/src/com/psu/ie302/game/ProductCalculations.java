package com.psu.ie302.game;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;

/*
 * Contains methods for calculations related to the products class
 */
public final class ProductCalculations {

	private static List<Integer> pickedProductsList = new ArrayList<Integer>();
	
	/*
	 * based off of:
	 * http://vinitwagh.blogspot.com/2008/07/irrinternal-rate-of-return-function.html
	 */
	/* TODO: SOMETIMES THE TWO POSSIBLE ANSWERS FOR THE COMBINED IRR ARE BOTH NEGATIVE,
	 *	WHICH RESULTS IN AN EXTREMELY LARGE COMBINED IRR. I'M NOT SURE HOW TO FIX THIS.
	 */
	public static BigDecimal calculateIRR(final int[] cashflows) {
		final int MAX_ITER = 20;
		BigDecimal EXCEL_EPSILON = new BigDecimal("0.0000001");
		BigDecimal x = new BigDecimal("0.1");
		int iter = 0;
		while (iter++ < MAX_ITER) {
			final BigDecimal x1 = BigDecimal.ONE.add(x);
			BigDecimal fx = BigDecimal.ZERO;
			BigDecimal dfx = BigDecimal.ZERO;
			for (int i = 0; i < cashflows.length; i++) {
				final BigDecimal v = new BigDecimal(cashflows[i]);
				final BigDecimal x1_i = x1.pow(i);
				//fx += v / x1_i;
				fx = fx.add(v.divide(x1_i, MathContext.DECIMAL128));
				//final double x1_i1 = x1_i * x1;
				final BigDecimal x1_i1 = x1_i.multiply(x1);
				//dfx += -i * v / x1_i1;
				dfx = dfx.add(((BigDecimal.valueOf(-i)).multiply(v)).divide(
						x1_i1, MathContext.DECIMAL128));
			}
			//final double new_x = x - (fx / dfx);
			final BigDecimal new_x = x.subtract(fx.divide(dfx, MathContext.DECIMAL128));
			//final double epsilon = Math.abs( new_x - x );
			final BigDecimal epsilon = (new_x.subtract(x)).abs();
//			if (epsilon <= EXCEL_EPSILON) {
//				if (x == 0.0 && Math.abs( new_x ) <= EXCEL_EPSILON) {
//				return 0.0;
//				} else {
//					return new_x;
//				}
//			}
			if (epsilon.compareTo(EXCEL_EPSILON) <= 0) {
				if (x.compareTo(BigDecimal.ZERO) == 0
						&& (new_x.abs()).compareTo(EXCEL_EPSILON) <= 0 ) {
					return BigDecimal.ZERO;
				} else {
					return new_x.setScale(4, BigDecimal.ROUND_HALF_UP);
				}
			}
			x = new_x;
		}
		return x.setScale(4, BigDecimal.ROUND_HALF_UP);
	}
	
	// returns IRR as a percentage
	public static String displayIRR(BigDecimal irr) {
		return irr.multiply(BigDecimal.valueOf(100)).setScale(2).toString() + "%";
	}
	
	// randomly pick an index for a product from the product list
	// (Keep list of products picked by indices and check that
	//	an index is not in the list before returning it. Calling class should
	//	refresh product index list when starting a new game.)
	public static int randomlyPickProduct(int max) {
		// randomly generate a product index
		int productRandIndex = MathUtils.random(max);
		
		// If product was already picked, then try a new one until
		// it generates one that wasn't picked yet.
		// Add a new product index to the list of used ones.
		while (pickedProductsList.contains(productRandIndex)) {
			productRandIndex = MathUtils.random(max);
		}
		pickedProductsList.add(productRandIndex);
		
		return productRandIndex;
	}
	
	public static void resetPickedProductsList() {
		pickedProductsList.clear();
	}
}
