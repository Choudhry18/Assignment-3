import java.util.Arrays;

public class FastCollinearPoints {
	private LineSegment list[] = new LineSegment[8];
	private int Counter;
	public FastCollinearPoints(Point[] points){
		if(points==null) throw new IllegalArgumentException();
		for(int i=0; i<points.length; i++)                                   //Checks the argument array.
			if(points[i]==null) throw new IllegalArgumentException();
		Point[] pointsArg = Arrays.copyOf(points, points.length);    //Makes a copy of the argument array to pass the "doesn't mutate constructor argument" test.
		int len = pointsArg.length;
		for(int i=0; i<len; i++) {
			Arrays.sort(pointsArg);                                         //sort is done every time to maintain stability.    
			if(i==0) {
				for(int j=0; j<len-1; j++)                                  // Checks for duplicate points.
					if(pointsArg[j].compareTo(pointsArg[j+1])==0) throw new IllegalArgumentException();
			}
			Point current = pointsArg[i];                                  //The points current is the origin point.
			Arrays.sort(pointsArg, current.slopeOrder());
			int Counter2 = 1;                                              //Counter 2 iterates through the sorted array inside the loop.
			while(Counter2+1<len) {
				int Counter3=0;                                           //Counter 3 keeps track of how many points in the line.
				double slope = current.slopeTo(pointsArg[Counter2++]);
				 while(slope == current.slopeTo(pointsArg[Counter2])){
					Counter3++;
					slope = current.slopeTo(pointsArg[Counter2++]);
					if(Counter2==len) break;                             //It is done to prevent IndexoutofBounds Exception.
				}
				Counter3++;                       //This increment is made because the first time the inner while loop runs actually two points are in the line 
				if(Counter3>=3) {                 //After that every time it runs one more point in in the line.
					if(current.compareTo(pointsArg[Counter2-Counter3])>0) continue;          //It is done to make sure the same segment is not made twice
					LineSegment n = new LineSegment(current, pointsArg[Counter2-1]);         //current is the starting point and Counter2 indexes the ending point
					list[Counter++] = n;
					if(Counter==list.length) list = Arrays.copyOf(list, list.length*2);
				}
			}
		}
    }
	
	public int numberOfSegments() {
		return Counter;
		
	}
	
	public LineSegment[] segments() {
		LineSegment[] arg = Arrays.copyOf(list, Counter);
		return  arg;
	}
}
