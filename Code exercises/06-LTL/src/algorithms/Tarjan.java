package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import ltlstructure.LTL;

public class Tarjan {

	int i = 0;
	Map<Set<LTL>, Set<Set<LTL>>> graph;
	Stack<Set<LTL>> points;
	private List<Set<Set<LTL>>> sccs;
	private Map<Set<LTL>, Set<LTL>> visitedStates;
	private Map<Set<LTL>, Integer> lowlinks;
	private Map<Set<LTL>, Integer> indexes;

	public Tarjan(Map<Set<LTL>, Set<Set<LTL>>> graph) {
		i = 0;
		points = new Stack<>();
		sccs = new ArrayList<>();
		visitedStates = new HashMap<>();
		this.graph = graph;
		
		lowlinks = new HashMap<Set<LTL>, Integer>();
		indexes = new HashMap<Set<LTL>, Integer>();
	}

	public List<Set<Set<LTL>>> getSccs() {
		return sccs;
	}

	public void runAlgorithm() {
		// -- for each vertex --//
		Iterator<Set<LTL>> iterator = graph.keySet().iterator();

		while (iterator.hasNext()) {

			Set<LTL> ltl = (Set<LTL>) iterator.next();

			// -- if not numbered/visited --//
			if (!visitedStates.containsKey(ltl)) {
				strongConnect(ltl);
			}
		}
	}

	public void strongConnect(Set<LTL> v) {
		i = i + 1;
		//v.index = i;
		indexes.put(v, i);
		//v.lowlink = i;
		lowlinks.put(v, i);
		visitedStates.put(v, v);
		points.push(v);

		Set<Set<LTL>> adjacency = graph.get(v);
		if (adjacency != null) {
			Iterator<Set<LTL>> iterator = adjacency.iterator();
			while (iterator.hasNext()) {
				Set<LTL> w = (Set<LTL>) iterator.next();
				// -- if not already numbered/visited
				if (!visitedStates.containsKey(w)) {
					strongConnect(w);
					//v.lowlink = min(v.lowlink, w.lowlink);
					lowlinks.put(v, min(lowlinks.get(v), lowlinks.get(w)));
				} else {
					Set<LTL> visitedW = visitedStates.get(w);
					//w.index = visitedW.index;
					indexes.put(w, indexes.get(visitedW));
					//w.lowlink = visitedW.lowlink;
					lowlinks.put(w, lowlinks.get(visitedW));
					if (points.contains(w)) {
						//v.lowlink = min(v.lowlink, w.index);
						lowlinks.put(v, min(lowlinks.get(v), indexes.get(w)));
					}
				}
			}
		}
		//if (v.lowlink == v.index) {
		if (lowlinks.get(v) == indexes.get(v)) {
			// -- start new strongly connected component --//
			Set<Set<LTL>> scc = new HashSet<>();
			// -- point on top of stack has number >= that of v
			//while (points.peek().index >= v.index) {
			while (indexes.get(points.peek()) >= indexes.get(v)) {
				Set<LTL> w = points.pop();
				scc.add(w);

				if (points.empty()) {
					break;
				}
			}
			// -- add this scc to set of all sccs
			sccs.add(scc);
		}

	}

	private int min(int a, int b) {
		if (a < b) {
			return a;
		}
		return b;
	}
}
