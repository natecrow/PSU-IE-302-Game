package com.psu.ie302.game;

/*
 * Contains methods for calculations related to the products class
 */
public final class ProductCalculations {

	/*
	 * based off of:
	 * http://vinitwagh.blogspot.com/2008/07/irrinternal-rate-of-return-function.html
	 */
	public static double calculateIRR(final int[] cashflows) {
		final int MAX_ITER = 20;
		double EXCEL_EPSILON = 0.0000001;
		double x = 0.1;
		int iter = 0;
		while (iter++ < MAX_ITER) {
			final double x1 = 1.0 + x;
			double fx = 0.0;
			double dfx = 0.0;
			for (int i = 0; i < cashflows.length; i++) {
				final double v = cashflows[i];
				final double x1_i = Math.pow( x1, i );
				fx += v / x1_i;
				final double x1_i1 = x1_i * x1;
				dfx += -i * v / x1_i1;
			}
			final double new_x = x - fx / dfx;
			final double epsilon = Math.abs( new_x - x );
		
			if (epsilon <= EXCEL_EPSILON) {
				if (x == 0.0 && Math.abs( new_x ) <= EXCEL_EPSILON) {
				return 0.0;
				} else {
					return new_x;
				}
			}
			x = new_x;
		}
		return x;
	}
}
