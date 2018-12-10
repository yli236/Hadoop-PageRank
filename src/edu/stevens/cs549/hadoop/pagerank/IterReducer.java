package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;
import java.util.*;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class IterReducer extends Reducer<Text, Text, Text, Text> {
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		double d = PageRankDriver.DECAY; // Decay factor
		/* 
		 * TODO: emit key:node+rank, value: adjacency list
		 * Use PageRank algorithm to compute rank from weights contributed by incoming edges.
		 * Remember that one of the values will be marked as the adjacency list for the node.
		 */
		
		Iterator<Text> it = values.iterator();
		double curr_rank = 0;
		String adj_list = "";
		while (it.hasNext()) {
			String next = it.next().toString();
			if (!next.startsWith("Adj_list")) {
				curr_rank += Double.valueOf(next);
				
			} else {
				adj_list = next.replaceAll("Adj_list", "");
			}
		}
		curr_rank = 1 - d + curr_rank * d;
		context.write(new Text(key + "+" + curr_rank), new Text(adj_list));
		
	}
}
