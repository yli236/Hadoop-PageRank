package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;
import java.util.*;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class DiffRed1 extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		double[] ranks = new double[2];
		/* 
		 * TODO: The list of values should contain two ranks.  Compute and output their difference.
		 */
		Iterator<Text> it = values.iterator();
		double diff = 0;
		if (it.hasNext()) {
			ranks[0] = Double.valueOf(it.next().toString());
		}
		
		if (it.hasNext()) {
			ranks[1] = Double.valueOf(it.next().toString());
		}
		
		diff = Math.abs(ranks[0] - ranks[1]);
		System.out.println(key + ": The diff is " + diff);
		context.write(key, new Text(String.valueOf(diff)));
	}
}
