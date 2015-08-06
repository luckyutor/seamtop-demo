package com.seamtop.demo.storm.wordcount;


import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class WordCountTopology {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, InterruptedException{
//        if(args != null){
//            for(int i=0;i<args.length;i++){
//                System.out.println(args[i]);
//            }
//        }
//        if (args.length != 2) {
//            System.err.println("Usage: inputPaht timeOffset");
//                    System.err.println("such as : java -jar WordCount.jar D://test/ 2");
//                            System.exit(2);
//        }
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReader());
        builder.setBolt("word-spilter", new WordSpliter()).shuffleGrouping("word-reader");
        builder.setBolt("word-counter", new WordCounter()).shuffleGrouping("word-spilter");
        String inputPaht = "D://test/";
        //String inputPaht = "/opt/seamtop/server/apache-storm-0.9.4/test";
        String timeOffset = "2";
        Config conf = new Config();
        conf.put("INPUT_PATH", inputPaht);
        conf.put("TIME_OFFSET", timeOffset);
        conf.setDebug(true);
        //以下两行为本机环境测试
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("WordCount", conf, builder.createTopology());
        //以下为集群环境测试
//        conf.put(Config.NIMBUS_HOST, "192.168.45.52");
//        conf.setNumWorkers(2);
//        StormSubmitter.submitTopologyWithProgressBar("WordCount2", conf, builder.createTopology());
    }
}

