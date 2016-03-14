import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Graph:The class which is used to create the vertices, associates an edge between the vertices of the graph
 * 
 * Graph is represented using Adjacency List Implementation
 */

public class Graph {

	private int assignRank = 0;

	

	// 'key' represents all the distinct vertices of the graph. The 'values'
	// represent the adjacent vertices of that particular key vertex.
	private HashMap<Vertex, List<Vertex>> adjList = new HashMap<Vertex, List<Vertex>>();

	// private boolean fromNodePresent=false;
	// private boolean toNodePresent=false;

	/*
	 * Checks if the vertex already exists (checks the hashmap). If yes, returns
	 * that vertex (from the map) else creates and returns a new vertex. Also
	 * whenever this method gets invoked, a new edge will be created to be
	 * associated with this Vertex.
	 */
	public void createVertices(String from, String to) {

		boolean fromNodePresent = false;
		boolean toNodePresent = false;

		Vertex fv = null;
		Vertex tv = null;

		Set<Map.Entry<Vertex, List<Vertex>>> set = adjList.entrySet();

		Iterator<Map.Entry<Vertex, List<Vertex>>> traverse = set.iterator();

		while (traverse.hasNext()) {

			Map.Entry<Vertex, List<Vertex>> vertices = traverse.next();

			if (vertices.getKey().getVertexName().equals(from)) {

				fromNodePresent = true;
				fv = vertices.getKey();
			}

			if (vertices.getKey().getVertexName().equals(to)) {

				toNodePresent = true;
				tv = vertices.getKey();
			}

		}

		if (fromNodePresent == false && toNodePresent == false) {

			fv = new Vertex(from);
			fv.setVertexRank(++assignRank);
			adjList.put(fv, new ArrayList<Vertex>());
			tv = new Vertex(to);
			tv.setVertexRank(++assignRank);
			adjList.put(tv, new ArrayList<Vertex>());
			adjList.get(fv).add(tv);
		}

		if (fromNodePresent == true && toNodePresent == false) {

			tv = new Vertex(to);
			tv.setVertexRank(++assignRank);
			adjList.put(tv, new ArrayList<Vertex>());
			adjList.get(fv).add(tv);
		}

		if (fromNodePresent == false && toNodePresent == true) {

			fv = new Vertex(from);
			fv.setVertexRank(++assignRank);
			adjList.put(fv, new ArrayList<Vertex>());
			adjList.get(fv).add(tv);
		}

		if (fromNodePresent == true && toNodePresent == true) {

			List<Vertex> vList = adjList.get(fv);
			if (!(vList.contains(tv))) {
				vList.add(tv);
			}

		}

	}

	/*
	 * Method that gives you the list of adjacent vertices of the input vertex
	 * 
	 * @return A List containing the adjacent vertices of the input vertex or
	 * null if there are no adjacent vertices for the given input vertex
	 */

	public List<Vertex> getAdjacentVertices(Vertex vertex) {

		List<Vertex> adjacentVertices = adjList.get(vertex);

		if (!(adjacentVertices == null)) {

			return adjacentVertices;
		} else {

			return null;

		}
	}

	/*
	 * Method provides all the vertices of your graph
	 * 
	 * @return List containing the vertices of the input graph or null if the
	 * graph has no vertices.
	 */
	public List<Vertex> getAllGraphVertices() {

		List<Vertex> graphVertices = new ArrayList<Vertex>();

		Set<Map.Entry<Vertex, List<Vertex>>> allVertices = adjList.entrySet();

		if (allVertices.isEmpty()) {
			return null;
		}

		Iterator<Map.Entry<Vertex, List<Vertex>>> verticestraversal = allVertices
				.iterator();

		while (verticestraversal.hasNext()) {
			Map.Entry<Vertex, List<Vertex>> individualEntry = verticestraversal
					.next();
			graphVertices.add(individualEntry.getKey());
		}

		return graphVertices;
	}

}
