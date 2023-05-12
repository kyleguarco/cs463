import java.util.ArrayList;

import graph.AdjacencyList;
import search.BreadthFirstSearch;
import search.DepthFirstSearch;

public class App {
	public static void main(String... args) {
		AdjacencyList<Integer> adjlist = new AdjacencyList<>();
		for (int i = 0; i < 5; i++)
			adjlist.insertVertex(i);
		adjlist.insertEdge(0, 1);
		adjlist.insertEdge(0, 2);
		adjlist.insertEdge(0, 4);
		adjlist.insertEdge(1, 2);
		adjlist.insertEdge(1, 3);
		adjlist.insertEdge(2, 3);

		// Prints out "4 0 1 2 3" while searching.
		ArrayList<Integer> visited = adjlist.search(DepthFirstSearch.withStartingVertex(0));
		visited.forEach(System.out::println);

		// Prints out "0 1 2 4 3" while searching.
		visited = adjlist.search(BreadthFirstSearch.withStartingVertex(0));
		visited.forEach(System.out::println);
	}
}
