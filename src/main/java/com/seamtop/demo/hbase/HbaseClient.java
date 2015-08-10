package com.seamtop.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zongjunfeng on 2015/8/10.
 */
public class HbaseClient {

    private static final Logger LOG = LoggerFactory.getLogger(HbaseClient.class);

    static Configuration conf = HBaseConfiguration.create();



    /**
     * 创建表结构
     * @param tableName
     * @param columnFamilys
     * @throws Exception
     */
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

    public static void insertList(String tableName,String rows[])throws Exception{
        HTable table = new HTable(conf,tableName);
        List<Put> list = new ArrayList<Put>();
        for(String r :rows){
            Put p = new Put(Bytes.toBytes(r));
            p.add(Bytes.toBytes("family1"),Bytes.toBytes("column1"),Bytes.toBytes("fff"));
            list.add(p);
        }
        table.put(list);
        table.close();
    }

    public static void main(String [] args) throws Exception{
        String[] str = new String[]{"family1","family2","family3"};
        //createTable("test-table",str);

    }
}
