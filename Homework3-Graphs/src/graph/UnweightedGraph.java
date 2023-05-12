package graph;

import java.util.List;

import search.UnweightedSearchable;

/** A graph without any edge objects */
public interface UnweightedGraph<V> {
	public List<UnweightedEdge<V>> incidentEdges(V vert);

	public boolean areAdjacent(V vert, V ohvert);

	public void insertVertex(V vert);

	public void insertEdge(V vert, V ohvert);

	public void removeVertex(V vert);

	public void removeEdge(UnweightedEdge<V> edge);

	public default <T, S extends UnweightedSearchable<V>> T search(S strategy) {
		return strategy.searchGraph(this);
	}
}
