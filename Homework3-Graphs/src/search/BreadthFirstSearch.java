package search;

import java.util.ArrayList;

import graph.UnweightedEdge;
import graph.UnweightedGraph;
import util.Queue;

public class BreadthFirstSearch<V> implements UnweightedSearchable<V> {
	public static <V> BreadthFirstSearch<V> withStartingVertex(V startingVertex) {
		return new BreadthFirstSearch<>(startingVertex);
	}

	private V startingVertex;

	private BreadthFirstSearch(V startingVertex) {
		this.startingVertex = startingVertex;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T, G extends UnweightedGraph<V>> T searchGraph(G graph) {
		return (T) this.bfs(graph, new ArrayList<>());
	}

	private <G extends UnweightedGraph<V>> ArrayList<V> bfs(G graph, ArrayList<V> visited) {
		Queue<V> queue = new Queue<>();
		visited.add(this.startingVertex);
		queue.enqueue(this.startingVertex);

		while (!queue.isEmpty()) {
			V vert = queue.dequeue().get();

			for (UnweightedEdge<V> edge : graph.incidentEdges(vert)) {
				// Find the vertex 'vert' is attached to... It could be 'ohvert' or 'vert'
				V other = vert == edge.ohvert() ? edge.vert() : edge.ohvert();

				if (!visited.contains(other)) {
					queue.enqueue(other);
					visited.add(other);
				}
			}
		}

		return visited;
	}
}
