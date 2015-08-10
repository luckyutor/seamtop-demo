package com.seamtop.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by zongjunfeng on 2015/8/10.
 */
public class HbaseClient {

    private static final Logger LOG = LoggerFactory.getLogger(HbaseClient.class);

    static Configuration conf = HBaseConfiguration.create();

    public static void main(String [] args) throws Exception{
        String[] str = new String[]{"aaaa","bbbb","cccc"};
        for(int i=0;i<10;i++){
            createTable("test30"+new Date().getTime(),str);
        }

    }

    public static void createTable(String tableName,String [] columnFamilys) throws Exception{
        HBaseAdmin admin = new HBaseAdmin(conf);
        System.out.println("admin:"+admin);
        if(admin.tableExists(tableName)){
            LOG.info("table exist now");
        }else{
            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
            for(String columnFamily:columnFamilys){
                tableDesc.addFamily(new HColumnDescriptor(columnFamily));
            }
            admin.createTable(tableDesc);
            LOG.info("create ok !");
      }

    }
}
