package game;

import java.util.ArrayList;

/**
* The Route class stores what routes are available form the current island
* 
* 
* @author Zoher Hussein, Jimmy Chu
*/
public class Route {
	
	/**
	 * A int list that contains information about route A
	 */
	private int[] routeA;
	
	/**
	 * A int list that contains information about route B
	 */
	private int[] routeB;
	
	/**
	 * A int list that contains information about route C
	 */
	private int[] routeC;
	
	/**
	 * A int list that contains information about route D
	 */
	private int[] routeD;
	
	
	
	/**
	 * @param islandRoutes array list of routes the island can take
	 */
	public Route(ArrayList<int[]> islandRoutes){
		
		//store routes in appropriate location
		routeA = islandRoutes.get(0);
		routeB = islandRoutes.get(1);
		routeC = islandRoutes.get(2);
		routeD = islandRoutes.get(3);
		
	}
	
	/**
	 * @return routeA information
	 */
	public int[] getRouteA() {
		
		return routeA;
		
	}
	
	/**
	 * @return routeB information
	 */
	public int[] getRouteB() {
		
		return routeB;
		
	}
	
	/**
	 * @return routeC information
	 */
	public int[] getRouteC() {
		
		return routeC;
		
	}
	
	/**
	 * @return routeD information
	 */
	public int[] getRouteD() {
		
		return routeD;
		
	}

	
}






