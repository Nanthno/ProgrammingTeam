import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

class Rockclimbing {

    public static void main(String[] args) {

	new Rockclimbing();
    }
    public Rockclimbing() {
	Scanner scanner = new Scanner(System.in);

	ArrayList<position> negatives = new ArrayList<position>();
	//HashMap<position, Integer> wall = new HashMap<position, Integer>();

	String line;
    
	line = scanner.nextLine();
	String[] a = line.split(" ");
	int height = Integer.valueOf(a[0]);
	int width = Integer.valueOf(a[1]);
        scanner.nextLine(); // skips line with all 'E's

	int[][] wall = new int[width][height];
    
	for(int i = 0; i < height; i++) {
	    line = scanner.nextLine();
	    a = line.split(" ");
	    for(int j = 0; j < width; j++) {
		int v = Integer.valueOf(a[j]);
		position p = new position(j, i);
	        wall[j][i] = v;
		if(v < 0) {
		    negatives.add(p);
		}
	    }
	}
	scanner.nextLine();

	
	ArrayList<position> endPoints = findLoops(negatives, wall);
	for(int i = 0; i < wall.length; i++) {
	    endPoints.add(new position(i, 0));
	}

	System.out.println(shortestPath(wall, endPoints));
	
    }

    public void printWall(int[][] wall) {
	for(int i = 0; i < wall.length; i++) {
	    System.out.println(Arrays.toString(wall[i]));
	}
    }
	

    class position {
	int x;
	int y;
	public String toString() {
	    return "("+x+", "+y+")";
	}
	public position(int a, int b){
	    x = a;
	    y = b;
	}
	public int getVal(int[][] wall) {
	    return wall[x][y];
	}
	public ArrayList<position> getAdjacentVal(int[][] wall) {
	    ArrayList<position> adj = new ArrayList<position>();
	    if(x > 0) // not on left edge
		adj.add(new position(x-1, y));
	    if(x < wall.length-1) // not on right edge
		adj.add(new position(x+1, y));
	    if(y > 0) // not at top
		adj.add(new position(x, y-1));
	    if(y < wall[0].length-1)
		adj.add(new position(x, y+1));

	    return adj;
	}
	public boolean equals(position p) {
	    return (p.x == x && p.y == y);
	}
	public boolean inArray(ArrayList<position> a) {
	    for(int i = 0; i < a.size(); i++) {
		if(this.equals(a.get(i))) {
		    return true;
		}
	    }
	    return false;
	}
    }

    public ArrayList<position> findLoops(ArrayList<position> a, int[][] wall) {
	ArrayList<position> loopStarts = new ArrayList<position>();
	
	for(int i = 0; i < a.size(); i++) {
	    position p = a.get(i);
	    if(p.getVal(wall) < 0) {
		ArrayList<position> adj = p.getAdjacentVal(wall);
		for(int j = 0; j < adj.size(); j++) {
		    position pa = adj.get(j);
		    int pav = pa.getVal(wall);
		    if(pav < 0) {
			if(!(p.inArray(loopStarts)))
			    loopStarts.add(p);
			if(!(pa.inArray(loopStarts)))
			    loopStarts.add(pa);
		    }
		    else if(pav < -1*p.getVal(wall) && !(pa.inArray(loopStarts))) {
			loopStarts.add(pa);
		    }
		}
	    }
	    
	}
	return loopStarts;
    }

    public int shortestPath(int[][] wall, ArrayList<position> ends) {

	ArrayList<path> que = new ArrayList<path>();
	ArrayList<position> visited = new ArrayList<position>();
	for(int i = 0; i < wall.length; i++) {
	    que.add(new path(new position(i, wall[0].length-1), wall[i][wall[0].length-1]));
	    visited.add(new position(i, wall[0].length-1));
	}
	while(true) {
	    System.out.println(que);
	    int index = nextSmallest(que);
	    path apath = que.get(index);
	    que.remove(index);
	    position p = apath.p;
	    int dist = apath.dist;
	    System.out.println("checking: " + apath);

	    if(ends.contains(p)) {
		return dist;
	    }

	    ArrayList<position> adj = p.getAdjacentVal(wall);
	    for(int i = 0; i < adj.size(); i++) {
		position pa = adj.get(i);
		if(!(pa.inArray(visited))) {
		    que.add(new path(pa, dist + pa.getVal(wall)));
		    visited.add(pa);
		}
	    }
	}
    }

    class path {
	position p;
	int dist;
	public path(position p, int dist) {
	    this.p = p;
	    this.dist = dist;
	}
	public String toString() {
	    return (p.toString() + ": " + dist);
	}
	
    }
    public int nextSmallest(ArrayList<path> a) {
	int smallest = Integer.MAX_VALUE;
	int index = 0;

	for(int i = 0; i < a.size(); i++) {
	    if(a.get(i).dist < smallest) {
		smallest = a.get(i).dist;
		index = i;
	    }
	}
	return index;
    }

	
	
		    
}    

	   
