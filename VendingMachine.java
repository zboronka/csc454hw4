import java.util.*;

public class VendingMachine {
	private int quarters;
	private int dimes;
	private int nickels;

	private int value;

	private TotalTime time = new TotalTime(0,0);

	public VendingMachine(int q, int d, int n, int v) {
		quarters = q;
		dimes = d;
		nickels = n;
		value = v;
	}

	public Collection<String> lambda() {
		return new ArrayList<String>();
	}

	public void delta(String input) {
	}

	public void deltaint() {
	}

	public void deltaext(int e, String input) {
	}

	public void deltacon(String input) {
	}
}
