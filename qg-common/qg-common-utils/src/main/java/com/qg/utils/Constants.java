package com.qg.utils;

public class Constants {
    //时间
    public static final int MAX_MINUTES=5;
    public static final int MIN_MINUTES=1;


    //抢购的状态
    public static final int STATUS_NO_LOGIN=1;
    public static final int STATUS_QG_DUP=1003;//已抢购过该商品
    public static final int STATUS_QG_FAIL=1102;//抢购失败
    public static final int STATUS_QG_PENDING=1103;//抢购等待处理

    //抢购用到的redis的key
    public static final String KEY_LOCK_GOODS_LOAD ="goodslock:";
    public static final String KEY_GOODS ="goods:";
    public static final String KEY_LOCK_GOODS_QG ="qg:lock:";
    public static final String KEY_QG_RESULT ="qg:result:";
    public static final String KEY_TEMP_STOCK ="qg:tempStock:";

    //抢购用的消息队列的交换机和路由
    public static final String QG_EXCHANGE ="qgExchange";
    public static final String QG_ROUTINGKEY ="qgMsgRouting";


    //消息队列的队列的名称，路由的名称
    public static final String MQ_QG_QUEUE="qg:message";
    public static final String MQ_QUEUE_ADD_TEMSTOCK ="add:tempstock";
    public static final String MQ_QUEUE_ADD_ORDER ="add:order";
    public static final String MQ_ROUTINGKEY_ADD_TEMSTOCK ="add:tempstock:routing";
    public static final String MQ_ROUTINGKEY_ADD_ORDER="add:order:routing";

    //task表中的type，任务类型
    public static final int TASK_TYPE_ADD_TEMPSTOCK=11;
    public static final int TASK_TYPE_UPDATE_TEMPSTOCK=12;
    public static final int TASK_TYPE_DEL_TEMPSTOCK=13;
    public static final int TASK_TYPE_ADD_ORDER=21;
    public static final int TASK_TYPE_UPDATE_ORDER=22;
    public static final int TASK_TYPE_DEL_ORDER=23;
    public static final int TASK_TYPE_ADD_TRADE=31;
    public static final int TASK_TYPE_UPDATE_STOCK=41;

    //task表中的状态
    public static final int TASK_STATUS_NO_SEND=1;//未发送
    public static final int TASK_STATUS_WAIT_PROCESS=2;//已发送未处理
    public static final int TASK_STATUS_PROCESS_FAIL=3;//处理失败
    public static final int TASK_STATUS_WAIT_SUCCESS=4;//处理成功
    public static final int TASK_STATUS_DELETED=5;//已删除


}
