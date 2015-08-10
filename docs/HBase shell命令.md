1������shell����  ./hbase shell

2���鿴���б�  list

3��ɾ����  ���� disable 't1'  Ȼ�� drop 't1'

4���鿴��ṹ  describe 't1'

5���������
    # �﷨��put <table>,<rowkey>,<family:column>,<value>,<timestamp>
    # ���磺����t1�����һ�м�¼��rowkey��rowkey001��family name��f1��column name��col1��value��value01��timestamp��ϵͳĬ��
    hbase(main)> put 't1','rowkey001','f1:col1','value01'

6����ѯĳ�м�¼
    # �﷨��get <table>,<rowkey>,[<family:column>,....]
    # ���磺��ѯ��t1��rowkey001�е�f1�µ�col1��ֵ
    hbase(main)> get 't1','rowkey001', 'f1:col1'
    # ���ߣ�
    hbase(main)> get 't1','rowkey001', {COLUMN=>'f1:col1'}
    # ��ѯ��t1��rowke002�е�f1�µ�������ֵ
    hbase(main)> get 't1','rowkey001'

7��ɨ���
    # �﷨��scan <table>, {COLUMNS => [ <family:column>,.... ], LIMIT => num}
    # ���⣬���������STARTROW��TIMERANGE��FITLER�ȸ߼�����
    # ���磺ɨ���t1��ǰ5������
    hbase(main)> scan 't1',{LIMIT=>5}

8����ѯ���е���������
    # �﷨��count <table>, {INTERVAL => intervalNum, CACHE => cacheNum}
    # INTERVAL���ö�������ʾһ�μ���Ӧ��rowkey��Ĭ��1000��CACHEÿ��ȥȡ�Ļ�������С��Ĭ����10�������ò�������߲�ѯ�ٶ�
    # ���磬��ѯ��t1�е�������ÿ100����ʾһ�Σ�������Ϊ500
    hbase(main)> count 't1', {INTERVAL => 100, CACHE => 500}

3��ɾ������
a )ɾ�����е�ĳ����ֵ
    # �﷨��delete <table>, <rowkey>,  <family:column> , <timestamp>,����ָ������
    # ���磺ɾ����t1��rowkey001�е�f1:col1������
    hbase(main)> delete 't1','rowkey001','f1:col1'

    ע����ɾ������f1:col1�����а汾������
b )ɾ����
    # �﷨��deleteall <table>, <rowkey>,  <family:column> , <timestamp>�����Բ�ָ��������ɾ����������
    # ���磺ɾ����t1��rowk001������
    hbase(main)> deleteall 't1','rowkey001'

c��ɾ�����е���������
    # �﷨�� truncate <table>
    # ���������ǣ�disable table -> drop table -> create table
    # ���磺ɾ����t1����������
    hbase(main)> truncate 't1'



