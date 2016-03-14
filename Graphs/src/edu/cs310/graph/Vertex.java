/*
 *  Class that represents the individual Vertex of a graph
 *  
 *  
 */
public class Vertex implements Comparable<Vertex> {
	
	//Variable used to keep track of the finishing time of a vertex while performing the DFS on the graph
	private int finishingTime=0;
	
	private String vertexName=null;
	
	//Variable used to keep track of the rank of a vertex while performing the DFS on the graph
	private int vertexRank=0;
	
	//Gets incremented whenever backtracking happens. 
	private int timestamp=0;
	
	//Used to keep track whether the vertex is visited or not.
	private boolean isNodeVisited=false;
	
	public Vertex(String vertexName){
		
		this.vertexName=vertexName;
	}
	
	public String getVertexName(){
		
		return vertexName;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isNodeVisited() {
		return isNodeVisited;
	}

	public void setNodeVisited(boolean isNodeVisited) {
		this.isNodeVisited = isNodeVisited;
	}

	public int getVertexRank() {
		return vertexRank;
	}

	public void setVertexRank(int vertexRank) {
		this.vertexRank = vertexRank;
	}

	@Override
	public int compareTo(Vertex that) {
		// TODO Auto-generated method stub
		return this.vertexRank>that.vertexRank?-1:1;
	}

	public int getFinishingTime() {
		return finishingTime;
	}

	public void setFinishingTime(int finishingTime) {
		this.finishingTime = finishingTime;
	}



}
