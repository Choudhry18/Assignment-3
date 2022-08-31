import java.util.Arrays;

public class BruteCollinearPoints {
	
	private LineSegment[] list = new LineSegment[8];                                  //This array stores the line segments, it is resized if needed.
	private int Counter;                                                              //It is the number of Line segments created.                
	public BruteCollinearPoints(Point[] points) {
		check(points);
		Counter = 0;
		list = new LineSegment[8];
		int len = 8;
		for(int i=0; i<points.length;i++) {                                          //4 nested for loops are used to combinations of four points if they are in a line.
			for(int j=0; j<points.length; j++) {
				if(i==j) continue;
				int ans = points[i].compareTo(points[j]);
				if(ans==0) throw new IllegalArgumentException();                    //These statements check for duplicate points.
				if(ans>0) continue;                                                 //These statements make sure that the point at Index i is the lowest point
				for(int k=0; k<points.length; k++) {
					if(k==i || k==j) continue;
					ans = points[j].compareTo(points[k]);
					if(ans==0) throw new IllegalArgumentException();
					if(ans>0)continue;
					for(int l=0; l<points.length; l++) {
						if(l==i || l==j || l==k) continue;
						ans = points[k].compareTo(points[l]);
						if(ans==0) throw new IllegalArgumentException();
						if(ans>0) continue;
						double slope = points[i].slopeTo(points[j]);
						if(slope == points[i].slopeTo(points[k]) && points[i].slopeTo(points[l]) == slope) {     //Checks if the four points are Collinear.
							LineSegment n = new LineSegment(points[i], points[l]);          //Creates the segment with points at index i and j. The former being the starting and latter the ending point.
							list[Counter++] = n;                                    //Adds the LineSegment to the arrays and increments Counter. 
							if(Counter >= len-1) {                                  //Checks if the arrays needs resizing if so then doubles the size.
								len = len*2;
								list = Arrays.copyOf(list, len);
							}
						}
					}
				}
			}
		}
	}
	
	public int numberOfSegments() {
		return Counter;
	}
	
	public LineSegment[] segments() {
		LineSegment[] arg = Arrays.copyOf(list, Counter);                       //returns a copy of the array to pass the immutability test.
		return arg;
	}
	
	private void check(Point[] points) {
		if(points == null) throw new IllegalArgumentException();
		for(int i = 0; i<points.length; i++) {
				if(points[i] == null) throw new IllegalArgumentException();
		}
	}
	
	
}


