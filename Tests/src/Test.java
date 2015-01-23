
public class Test {

	public static void main(String[] args) {
		System.out.println(2*((18+2)/10 - 18/100));
		System.out.println(87/10);
		System.out.println(110/5);
		int n = 5;
		int i;
		int j;
		for (i=1; i<=n; i++) {
			for (j=1; j<=(n-i); j++) {
				System.out.print(" ");
			}
			for (j=1; j<=(n-(n-i)); j++) {
				System.out.print("* ");
			}
			System.out.print("\n");
		}
		System.out.println();
		for (i=1; i<=n; i++) {
			for (j=1; j<=(n-(n-i)); j++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}

}
