public class hw4 {
	public static void main(String[] args) {
		Port<String> i = new Port<>();
		Port<String> o = new Port<>();

		VendingMachine m = new VendingMachine(0,0,0,0);
		m.addInput(i);
		m.addOutput(o);
		i.set("q");
		for(int j = 0; j < 60; j ++) {
			switch(j) {
				case 1: case 2: case 3:
					i.set("q");
					break;
				case 4: case 5: case 6:
					i.set("d");
					break;
				default:
					break;
			}

			try {
				m.Delta();
				m.Lambda();
				if(o.available()) {
					System.out.println("Time is " + j);
					System.out.println(o.get());
				}
			}
			catch(NoChangeException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}
}
