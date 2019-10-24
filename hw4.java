import java.util.Scanner;
import java.util.LinkedList;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class hw4 {
	public static void main(String[] args) {
		Port<String> i = new Port<>();
		Port<String> o = new Port<>();

		VendingMachine m = new VendingMachine(0,0,0,0);
		m.addInput(i);
		m.addOutput(o);

		Scanner sc = new Scanner(System.in);
		String trajectory = sc.nextLine();

		Pattern p = Pattern.compile("\\((\\d),([qdn])\\)");
		Matcher match = p.matcher(trajectory);

		LinkedList<Integer> times = new LinkedList<>();
		LinkedList<String> inputs = new LinkedList<>();

		while(match.find()) {
			times.add(Integer.parseInt(match.group(1)));
			inputs.add(match.group(2));
		}

		int last_time = times.peekLast() + 21;

		for(int j = 0; j <= last_time; j ++) {
			if(times.peekFirst() != null && j == times.peekFirst()) {
				times.removeFirst();
				i.set(inputs.removeFirst());
			}

			try {
				m.Lambda();
				m.Delta();
				if(o.available()) {
					System.out.println("Time is " + j);
					System.out.println(o.get());
				}
			} catch(NoChangeException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}
}
