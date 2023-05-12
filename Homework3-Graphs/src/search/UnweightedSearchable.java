package search;

import graph.UnweightedGraph;

/** A search algorithm that deals with null edge objects. */
public interface UnweightedSearchable<V> {
	<T, G extends UnweightedGraph<V>> T searchGraph(G graph);
}
