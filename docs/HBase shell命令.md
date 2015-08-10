1、进入shell命令  ./hbase shell

2、查看表列表  list

3、删除表  首先 disable 't1'  然后 drop 't1'

4、查看表结构  describe 't1'

5、添加数据
    # 语法：put <table>,<rowkey>,<family:column>,<value>,<timestamp>
    # 例如：给表t1的添加一行记录：rowkey是rowkey001，family name：f1，column name：col1，value：value01，timestamp：系统默认
    hbase(main)> put 't1','rowkey001','f1:col1','value01'

6、查询某行记录
    # 语法：get <table>,<rowkey>,[<family:column>,....]
    # 例如：查询表t1，rowkey001中的f1下的col1的值
    hbase(main)> get 't1','rowkey001', 'f1:col1'
    # 或者：
    hbase(main)> get 't1','rowkey001', {COLUMN=>'f1:col1'}
    # 查询表t1，rowke002中的f1下的所有列值
    hbase(main)> get 't1','rowkey001'

7、扫描表
    # 语法：scan <table>, {COLUMNS => [ <family:column>,.... ], LIMIT => num}
    # 另外，还可以添加STARTROW、TIMERANGE和FITLER等高级功能
    # 例如：扫描表t1的前5条数据
    hbase(main)> scan 't1',{LIMIT=>5}

8、查询表中的数据行数
    # 语法：count <table>, {INTERVAL => intervalNum, CACHE => cacheNum}
    # INTERVAL设置多少行显示一次及对应的rowkey，默认1000；CACHE每次去取的缓存区大小，默认是10，调整该参数可提高查询速度
    # 例如，查询表t1中的行数，每100条显示一次，缓存区为500
    hbase(main)> count 't1', {INTERVAL => 100, CACHE => 500}

3）删除数据
a )删除行中的某个列值
    # 语法：delete <table>, <rowkey>,  <family:column> , <timestamp>,必须指定列名
    # 例如：删除表t1，rowkey001中的f1:col1的数据
    hbase(main)> delete 't1','rowkey001','f1:col1'

    注：将删除改行f1:col1列所有版本的数据
b )删除行
    # 语法：deleteall <table>, <rowkey>,  <family:column> , <timestamp>，可以不指定列名，删除整行数据
    # 例如：删除表t1，rowk001的数据
    hbase(main)> deleteall 't1','rowkey001'

c）删除表中的所有数据
    # 语法： truncate <table>
    # 其具体过程是：disable table -> drop table -> create table
    # 例如：删除表t1的所有数据
    hbase(main)> truncate 't1'



