package graph;

import java.util.Objects;

/** Holds a reference to two verticies. */
public record UnweightedEdge<V>(V vert, V ohvert) {
	public UnweightedEdge {
		Objects.requireNonNull(vert);
		Objects.requireNonNull(ohvert);
	}
}
