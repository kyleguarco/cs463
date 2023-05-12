package search;

import java.util.ArrayList;

import graph.UnweightedEdge;
import graph.UnweightedGraph;

public class DepthFirstSearch<V> implements UnweightedSearchable<V> {
	public static <V> DepthFirstSearch<V> withStartingVertex(V startingVertex) {
		return new DepthFirstSearch<>(startingVertex);
	}

	private V startingVertex;

	private DepthFirstSearch(V startingVertex) {
		this.startingVertex = startingVertex;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T, G extends UnweightedGraph<V>> T searchGraph(G graph) {
		return (T) this.dfs(graph, new ArrayList<>(), this.startingVertex);
	}

	private <G extends UnweightedGraph<V>> ArrayList<V> dfs(G graph, ArrayList<V> visited, V currentNode) {
		visited.add(currentNode);

		for (UnweightedEdge<V> edge : graph.incidentEdges(currentNode)) {
			// This *is* ugly, but we need to check both edges....
			V vert = currentNode == edge.ohvert() ? edge.vert() : edge.ohvert();

			if (!(visited.contains(vert))) {
				this.dfs(graph, visited, vert);
			}
		}

		return visited;
	}
}
