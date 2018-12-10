# Hadoop-PageRank

## Description
A simple implementation of the iterative PageRank algorithm to find the most popular pages in a dump of Wikipedia pages.



Input is provided as follows: node-id: to-node1 to-node2 ...

## Motivation
Implementation of a distributed systems project assignment to explore the Apache Hadoop framework.

## Installation
Follow [this link](https://github.com/MuhammadBilalYar/Hadoop-On-Window/wiki/Step-by-step-Hadoop-2.8.0-installation-on-Window-10) to install and setup Java and Hadoop.<br />
<br />
Compile the source into an executable jar file through your method of choice.<br />
<br />
Put input data to the repository: 
```
hdfs dfs -put Samples.txt /input
```


## Usage
To run the application: 
```
hadoop jar PageRank-1.0.0.jar edu.stevens.cs549.hadoop.pagerank.PageRankDriver /input /output inter1 inter2 diffdir 10
```
### MapReduce functions included: 
```
init      <input directory>
          <output directory>
          <#reducers>
          
diff      <input directory>
          <output directory>
          <#reducers>
          
finish    <input directory
          <output directory>
          <#reducers>
          
composite <input directory>
          <output directory>
          <interim directory 1>
          <interim directory 2> 
          <difference directory>
          <#reducers>
          ```
          
