import java.util.Scanner;

import java.util.ArrayList;

class LostMap {
    static ArrayList<String> roads;
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);

	int numInputs = Integer.parseInt(scanner.nextLine());

        int[][] villages = new int[numInputs][numInputs];
	
	for(int i = 0; i < numInputs; i++) {
	    String lineS = scanner.nextLine();
	    String[] line = lineS.split(" ");
	    for(int j = 0; j < line.length; j++) {
	        villages[i][j] = Integer.parseInt(line[j]);
	    }
	}

	/*for(int[] v : villages) {
	    for(int i : v) {
		System.out.print(i + " ");
	    }
	    System.out.println("");
	    }*/

	// convert villages into some data structure...

        roads = new ArrayList<String>();

	// solve it...

	

	System.out.println(roads.toString());

	

	
    }

    static boolean checkInRoads(int a, int b) {
	String road;
	if(a > b) {
	    road = a + ":" +b;
	}
	else {
	    road = b + ":"+a;
	}
	return roads.contains(road);
    }

    static void addRoad(int a, int b) {
	if(!checkInRoads(a, b)) {
	    if(a > b) {
		roads.add(a+":"+b);
	    }
	    else{
		roads.add(b+":"+a);
	    }
	}
    }
		    
	
	    
}
