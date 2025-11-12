package Minesweeper;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomGenerator {
	public final static Random rand = new Random();
	
	public final static int[] computeRandomArray(int length_of_origin, int length_of_target) {
		float[] randFloat = new float[length_of_origin];
		for(int i = 0; i<length_of_origin; i++) {
			randFloat[i] = RandomGenerator.rand.nextFloat();
		}
		
		int[] indices = IntStream.range(0, length_of_origin)
				.boxed()
				.sorted((a,b) -> Float.compare(randFloat[a], randFloat[b]))
				.mapToInt(Integer::intValue).toArray();
		
		return Arrays.copyOf(indices, length_of_target);
	}
}
