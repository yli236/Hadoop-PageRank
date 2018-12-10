package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class IterMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException,
			IllegalArgumentException {
		String line = value.toString(); // Converts Line to a String
		String[] sections = line.split("\t"); // Splits it into two parts. Part 1: node+rank | Part 2: adj list

		if (sections.length > 2) // Checks if the data is in the incorrect format
		{
			throw new IOException("Incorrect data format");
		}
		if (sections.length != 2) {
			return;
		}
		
		/* 
		 * TODO: emit key: adj vertex, value: computed weight.
		 * 
		 * Remember to also emit the input adjacency list for this node!
		 * Put a marker on the string value to indicate it is an adjacency list.
		 */
		String[] nodeAndRank = sections[0].split("\\+");
		String nodeId = String.valueOf(nodeAndRank[0]);
		double rank = Double.valueOf(nodeAndRank[1]);
		String adj_list = sections[1].toString().trim();
		String[] adj_nodes = adj_list.split(" ");
		int linksOutNum = adj_nodes.length;  // the Number of links out
		double computedWeight = 1/linksOutNum * rank; // rank/num of links out
		for (String node: adj_nodes) {
			context.write(new Text(node), new Text(String.valueOf(computedWeight))); // emit node and computed weight
		}
		
		// emit this node and the adj_list
		context.write(new Text(nodeId), new Text("Adj_list" + sections[1]));
		
		
		

	}

}
