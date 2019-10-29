import java.util.*;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class VendingMachine {
	private int quarters;
	private int dimes;
	private int nickels;

	private int value;

	private TotalTime time = new TotalTime(0,0);
	private int e;

	public ArrayList<Port<String>> inputs = new ArrayList<>();
	public ArrayList<Port<String>> outputs = new ArrayList<>();

	public VendingMachine(int q, int d, int n, int v) {
		quarters = q;
		dimes = d;
		nickels = n;
		value = v;
		e = 0;
	}

	private int ta() {
		return value > 0 ? 20 : -1;
	}

	public void Lambda() throws NoChangeException {
		if(e == ta()) {
			for(Port<String> output : outputs) {
				output.set(lambda());
			}
		}
	}

	public void Delta() throws NoChangeException {
		for(Port<String> input : inputs) {
			if(input.available() && (e < ta() || ta() < 0)) {
				deltaext(input.get());
				time.advance(new TotalTime(0,1));
				e = 1;
			}
			else if(input.available() && e == ta()) {
				deltacon(input.get());
				time.advance(new TotalTime(0,1));
				e = 1;
			}
			else if(!input.available() && e == ta()) {
				deltaint();
				time.advance(new TotalTime(0,1));
				e = 1;
			}
			else {
				time.advance(new TotalTime(1,0));
				e++;
			}
		}
	}

	public void addInput(Port<String> i) {
		inputs.add(i);
	}

	public void addOutput(Port<String> o) {
		outputs.add(o);
	}

	private String lambda() throws NoChangeException {
		String ret = "";

		ret += getChange(value % 100);

		for(int i = 0; i < value / 100; i++) {
			ret += "<coffee>";
		}

		return ret;
	}

	private void deltaint() throws NoChangeException {
		Pattern p = Pattern.compile("<(\\w*)>");
		Matcher change = p.matcher(getChange(value % 100));

		while(change.find()) {
			switch(change.group(0)) {
				case "quarter":
					quarters--;
					break;
				case "dime":
					dimes--;
					break;
				case "nickel":
					nickels--;
					break;
				default:
					break;
			}
		}

		value = 0;
	}

	private void deltaext(String input) {
		switch(input) {
			case "q": 
				quarters++;
				value+=25;
				break;
			case "d":
				dimes++;
				value+=10;
				break;
			case "n":
				nickels++;
				value+=5;
				break;
			default:
				break;
		}
	}

	private void deltacon(String input) throws NoChangeException {
		deltaint();
		deltaext(input);
	}

	private String getChange(int v) throws NoChangeException {
		String ret = "";

		int q = v / 25 > quarters ? quarters : v / 25;
		int d = (v-q*25) / 10 > dimes ? dimes : (v-q*25) / 10;
		int n = ((v-q*25)-d*10) / 5 > nickels ? nickels : ((v-q*25)-d*10) / 5;

		if(q*25 + d*10 + n*5 != v) {
			throw new NoChangeException("Not enough change in machine");
		}

		for(int i = 0; i < q; i++) {
			ret += "<quarter>";
		}
		for(int i = 0; i < d; i++) {
			ret += "<dime>";
		}
		for(int i = 0; i < n; i++) {
			ret += "<nickel>";
		}

		return ret;
	}
}
