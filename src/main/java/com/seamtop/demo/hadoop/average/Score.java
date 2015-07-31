package com.seamtop.demo.hadoop.average;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by zongjunfeng on 2015/7/31.
 */
public class Score {

    public static class Map extends Mapper<LongWritable,Text,Text,IntWritable> {

        //实现MAP函数
        public void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException{
            //将输入的纯文本文件的数据转换成String
            String line = value.toString();
            System.out.println("line======"+line);
            StringTokenizer tokenizerArticle = new StringTokenizer(line,"\n");
            while(tokenizerArticle.hasMoreElements()){
                StringTokenizer tokenizerLine = new StringTokenizer(tokenizerArticle.nextToken());
                String strName = tokenizerLine.nextToken();
                String strScore = tokenizerLine.nextToken();
                Text name = new Text(strName);
                int scoreInt = Integer.parseInt(strScore);
                context.write(name,new IntWritable(scoreInt));
            }
        }
    }

    public static class Reduce extends Reducer<Text,IntWritable,Text,IntWritable> {
        //实现Reduce函数
        public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException,InterruptedException{
            System.out.println("Reduce:key:"+key+" values:"+values);
            int sum = 0;
            int count = 0;
            Iterator <IntWritable> iterator = values.iterator();
            while(iterator.hasNext()){
                sum += iterator.next().get();
                count ++;
            }
            int average = (int)sum /count;
            context.write(key,new IntWritable(average));
        }
    }

    public static void main(String [] args) throws Exception{
        Configuration conf = new Configuration();
        String [] ioArgs = new String[]{"/score_in","/score_out"};
        conf.set("mapreduce.jobtracker.address","192.168.45.52:9001");
        String [] otherArgs = new GenericOptionsParser(conf,ioArgs).getRemainingArgs();
        if(otherArgs.length != 2){
            System.err.println("Usage:Score Average <in> <out>");
            System.exit(2);
        }
        Job job = new Job(conf,"Score Average");
        job.setJarByClass(Score.class);
        job.setMapperClass(Map.class);
        job.setCombinerClass(Reduce.class);
        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //将输入数据集分成小块
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
