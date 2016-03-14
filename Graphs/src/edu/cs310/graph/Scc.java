import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 *  Class that is used for finding the Strongly Connected Components in a directed graph 
 */
public class Scc {
	private static int pass=0;
	private List<Vertex> pass2Vertices=new ArrayList<Vertex>();
	private Map<Integer,List<Vertex>> map=new HashMap<Integer,List<Vertex>>();
	private int fTime=0;
	/*
	 *  Used to perform DFS on all the nodes of a graph
	 *  This list(List<Vertex> sortedVertices) contains the vertices in descending order.
	 *  
	 *  Running Time : O(n) where 'n' represents the number of vertices of the graph
	 */
	
	public void DFSLoop(Graph graph, List<Vertex> sortedVertices){
		
		int index=0;
		pass++;
		
		List<Vertex> visited=new ArrayList<Vertex>();
		
		for(Vertex v:sortedVertices){
			
			if(!v.isNodeVisited()){
				
				DFS(graph, v);
				
				if(pass==2){
				index++;
				map.put(index, new ArrayList<Vertex>());
				
				for(Vertex vTemp:pass2Vertices){
					
					
					map.get(index).add(vTemp);
				}
				
				pass2Vertices.removeAll(pass2Vertices);
			  }
			}
		}
		if(pass==2){
		Set<Map.Entry<Integer,List<Vertex>>> finalSet=map.entrySet();
		Iterator<Map.Entry<Integer,List<Vertex>>> sccTraversor=finalSet.iterator();
		//System.out.println("-----------------Inside Pass2---------------------");
		while(sccTraversor.hasNext()){
			List<Vertex> list=sccTraversor.next().getValue();
			String str="[";
			for(Vertex vTemp:list){
				//System.out.println(vTemp.getVertexName());
				str=str+vTemp.getVertexName()+",";
			}
			str=str+"]";
			str=str.replaceAll(",]", "]");
			System.out.println(str);
			//System.out.println("----------------Pass 2 finished----------------------");
		  }
		}	
		
	}
	
	/*
	 *  Used to perform DFS on the graph starting on the given vertex
	 *  
	 *  Running Time O(V+E) 
	 */
	public void DFS(Graph graph,Vertex startVertex){
		Vertex v1=null;
		startVertex.setNodeVisited(true);
		if(pass==2){
			pass2Vertices.add(startVertex);
		}
		//System.out.println(startVertex.getVertexName());
		List<Vertex> adjVertices=graph.getAdjacentVertices(startVertex);
		if(!adjVertices.isEmpty()){
			for(Vertex v:adjVertices){
				
				if(!v.isNodeVisited()){
					v1=v;
					
					DFS(graph,v);
				}
			}
			
			
		}
		if(pass!=2){
			startVertex.setFinishingTime(++fTime);
		}
		
	}
  }
