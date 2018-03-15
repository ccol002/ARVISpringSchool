package algorithms;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ltlstructure.LTL;

public class Helper {

	public static Set<Set<LTL>> setOfAllSubsets(List<LTL> sigma) {
		Set<Set<LTL>> set = new LinkedHashSet<>();

		for (int i = 0; i < (int) Math.pow(2, sigma.size()); i++) {
			Set<LTL> tempSet = new LinkedHashSet<>();
			String binary = Integer.toBinaryString(i);
			// -- pad with zeros if necessary --//
			if (sigma.size() > binary.length()) {
				for (int j = binary.length(); j < sigma.size(); j++) {
					binary = "0" + binary;
				}
			}
			// -- check which props to add--//
			for (int j = 0; j < sigma.size(); j++) {
				if (binary.charAt(j) == '1') {
					tempSet.add(sigma.get(j));
				}
			}
			set.add(tempSet);
		}

		return set;
	}
	
	public static Set<Set<Set<LTL>>> setOfAllSets(List<Set<LTL>> sigma) {
		Set<Set<Set<LTL>>> set = new LinkedHashSet<>();

		for (int i = 0; i < (int) Math.pow(2, sigma.size()); i++) {
			Set<Set<LTL>> tempSet = new LinkedHashSet<>();
			String binary = Integer.toBinaryString(i);
			// -- pad with zeros if necessary --//
			if (sigma.size() > binary.length()) {
				for (int j = binary.length(); j < sigma.size(); j++) {
					binary = "0" + binary;
				}
			}
			// -- check which props to add--//
			for (int j = 0; j < sigma.size(); j++) {
				if (binary.charAt(j) == '1') {
					tempSet.add(sigma.get(j));
				}
			}
			set.add(tempSet);
		}

		return set;
	}
	
	public static Set<Set<String>> setOfAllStrings(List<String> sigma) {
		Set<Set<String>> set = new LinkedHashSet<>();

		for (int i = 0; i < (int) Math.pow(2, sigma.size()); i++) {
			Set<String> tempSet = new LinkedHashSet<>();
			String binary = Integer.toBinaryString(i);
			// -- pad with zeros if necessary --//
			if (sigma.size() > binary.length()) {
				for (int j = binary.length(); j < sigma.size(); j++) {
					binary = "0" + binary;
				}
			}
			// -- check which props to add--//
			for (int j = 0; j < sigma.size(); j++) {
				if (binary.charAt(j) == '1') {
					tempSet.add(sigma.get(j));
				}
			}
			set.add(tempSet);
		}

		return set;
	}
	
	public static Set<Set<LTL>> setOfAllProps(List<LTL> sigma) {
		Set<Set<LTL>> set = new LinkedHashSet<>();
		
		for (LTL prop : sigma) {
			Set<LTL> temp = new LinkedHashSet<>();
			temp.add(prop);
			set.add(temp);
		}
		
		return set;
	}
}
