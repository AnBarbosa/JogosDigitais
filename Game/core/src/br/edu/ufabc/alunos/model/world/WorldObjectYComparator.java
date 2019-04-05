package br.edu.ufabc.alunos.model.world;

import java.util.Comparator;

public class WorldObjectYComparator implements Comparator<YSortable> {

	@Override
	public int compare(YSortable objectA, YSortable objectB) {
		if(objectA.getWorldY() > objectB.getWorldY()) {
			return +1;
		} else if (objectA.getWorldY() < objectB.getWorldY()) {
			return -1;
		}
		return 0;
	}
}
