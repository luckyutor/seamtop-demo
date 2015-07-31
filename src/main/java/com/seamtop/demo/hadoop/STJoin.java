package com.seamtop.demo.hadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by zongjunfeng on 2015/7/31.
 */
public class STJoin {

    public static class Map extends Mapper<Object,Text,Text,Text>{
        public static int time = 0;
        public void map(Object key,Text value,Mapper.Context context) throws IOException,InterruptedException{
            String childname = new String();
            String parentname = new String();
            String relationtype = new String();
            StringTokenizer itr = new StringTokenizer(value.toString());
            String [] values = new String[2];
            int i=0;
            while(itr.hasMoreTokens()){
                values[i] = itr.nextToken();
                i++;
            }
            if(values[0].compareTo("child") != 0){
                childname = values[0];
                parentname = values[1];
                relationtype = "1";
                context.write(new Text(values[1]),new Text(relationtype + "+" + childname +"+"+parentname));
                relationtype = "2";
                context.write(new Text(values[0]),new Text(relationtype + "+" + childname +"+"+parentname));
            }
        }


        public static class Reduce extends Reducer<Text,Text,Text,Text> {
            public void reduce(Text key,Iterable<Text> values,Context context) throws IOException,InterruptedException{
                if(0 == time){
                    context.write(new Text("grandchild"),new Text("grandparent"));
                    time ++;
                }
                int grandchildnum = 0;
                String [] grandchild = new String[10];
                int grandparentnum = 0;
                String [] grandparent = new String[10];
                Iterator ite = values.iterator();
                while(ite.hasNext()){
                    String record = ite.next().toString();
                    int len = record.length();
                    int i = 2;
                    if (0 == len){
                        continue;
                    }
                    char relationtype = record.charAt(0);
                    String childname = new String();
                    String parentname = new String();
                    while(record.charAt(i) != '+'){
                        childname += record.charAt(i);
                        i++;
                    }
                    i = i + 1;
                    while(i < len){
                        parentname += record.charAt(i);
                        i++;
                    }

                    if('1' == relationtype){

                    }
                }
            }
        }

    }
}
