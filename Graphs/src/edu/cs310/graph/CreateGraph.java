import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CreateGraph {

	private Graph fwdGraph = new Graph();
	private Graph revGraph = new Graph();
	List<Vertex> vertices = null;
	List<Vertex> fVertices = null;

	/*
	 * Reads the input file and creates graph.
	 * 
	 * @throws IllegalArgumentsException when the number of arguments in the input is not proper in the input
	 * file
	 * 
	 * @throws NumberFormatException when there is some error in accessing the
	 * input
	 * 
	 * @throws FileNotFoundException if the file named twoScc.txt is not found
	 * in the project directory location Handles the Number Format Exception
	 * when the first line in the input file does not indicate the number of
	 * vertices and the number of edges
	 */
	public void handleFileInput() throws IOException {

		File file = new File("twoScc.txt");
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			line = reader.readLine();
			String[] dissect = line.split(" ");
			if (dissect.length != 2) {
				throw new IllegalArgumentsException(
						"The number of Input Arguments representing the edges and vertices is not correct");
			} else {
				// Checks whether the first line in the input file properly
				// represents the num of edges and num of vertices
				try {
					Integer num1 = new Integer(dissect[0]);
					Integer num2 = new Integer(dissect[1]);
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					System.exit(1);
				}

			}
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				String[] parse = line.split(" ");
				if (parse.length > 2 || parse.length == 0) {
					throw new IllegalArgumentsException(
							"Input not proper. Shutting down the application");
				} else {
					createFwdGraph(fwdGraph, parse[0], parse[1]);
					createRevGraph(revGraph, parse[1], parse[0]);
				}
			}
		} else {
			System.out.println("File Not Found");
			throw new FileNotFoundException("File named SCC.txt is not found");
		}

		this.computeRevScc(revGraph);
		this.computeFwdScc(fwdGraph);

	}

	/*
	 * Constructs the forward graph. This graph will be used in the second pass
	 * while calculating the Strongly Connected Components
	 * 
	 * @ returns Graph with complete vertices
	 */
	public Graph createFwdGraph(Graph fwd, String from, String to) {
		fwd.createVertices(from, to);
		return fwd;
	}

	/*
	 * Constructs the reverse graph. This graph will be used in the first pass
	 * while calculating the Strongly Connected Components
	 * 
	 * @ returns the reversed Graph with complete vertices
	 */
	public Graph createRevGraph(Graph rev, String from, String to) {
		rev.createVertices(from, to);
		return rev;
	}

	/*
	 * Method that does invokes the DFS-Loop of the reversed graph. Used for
	 * finding the finishing time of the vertices
	 */
	public void computeRevScc(Graph revgraph) {
		vertices = revgraph.getAllGraphVertices();
		Scc scc = new Scc();
		// Sorting the Vertex List. Note:The Vertex class implements the
		// Comparable interface which facilitates this sorting of the Vertex
		// List based on the ranking of the vertices
		Collections.sort(vertices);
		scc.DFSLoop(revgraph, vertices);
	}

	/*
	 * Method that invokes the DFS-Loop of the forward(Original) graph.
	 * 
	 */

	public void computeFwdScc(Graph fwdgraph) {
		fVertices = fwdGraph.getAllGraphVertices();

		// Assigning the finishing time to the forward graph(Original graph)

		for (Vertex rVer : vertices) {
			for (Vertex fVer : fVertices) {
				if (rVer.getVertexName().equals(fVer.getVertexName())) {
					fVer.setFinishingTime(rVer.getFinishingTime());
					break;
				}
			}
		}

		Scc scc2 = new Scc();
		// Note:FinishingTimeComparator is a comparator that I have used which
		// will sort the vertex list based on the Finishing Time of the vertices
		Collections.sort(fVertices, new FinishingTimeComparator());
		scc2.DFSLoop(fwdGraph, fVertices);
	}

}
