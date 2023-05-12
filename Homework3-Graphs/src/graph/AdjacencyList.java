package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AdjacencyList<V> implements UnweightedGraph<V> {
	private HashMap<V, ArrayList<UnweightedEdge<V>>> edgelist;

	public AdjacencyList() {
		this.edgelist = new HashMap<>();
	}

	@Override
	public List<UnweightedEdge<V>> incidentEdges(V vert) {
		return edgelist.get(vert);
	}

	@Override
	public boolean areAdjacent(V vert, V ohvert) {
		for (UnweightedEdge<V> edge : edgelist.get(vert)) {
			// It could either be 'vert' or 'ohvert' (see 'insertEdge')
			if (edge.ohvert() == ohvert || edge.vert() == ohvert) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void insertVertex(V vert) {
		edgelist.put(vert, new ArrayList<>());
	}

	@Override
	public void insertEdge(V vert, V ohvert) {
		UnweightedEdge<V> edge = new UnweightedEdge<>(vert, ohvert);
		// We create an edge that the two lists share. This will make
		// removing the edge later easier, since we just use object hashcodes
		// to identify them.
		edgelist.get(vert).add(edge);
		edgelist.get(ohvert).add(edge);
	}

	@Override
	public void removeVertex(V vert) {
		ArrayList<UnweightedEdge<V>> oldedges = edgelist.remove(vert);

		for (UnweightedEdge<V> edge : oldedges) {
			// One of the edges exist, but we don't remember which.
			ArrayList<UnweightedEdge<V>> vlist = Optional
					.ofNullable(edgelist.get(edge.vert()))
					.orElse(edgelist.get(edge.ohvert()));
			// This modifies the list entry in the hashmap.
			vlist.remove(edge);
		}
	}

	@Override
	public void removeEdge(UnweightedEdge<V> edge) {
		edgelist.get(edge.vert()).remove(edge);
		edgelist.get(edge.ohvert()).remove(edge);
	}
}
