package com.qg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.qg.pojo.QgGoods;
import com.qg.pojo.QgGoodsTempStock;
import com.qg.pojo.QgOrder;
import com.qg.service.QgGoodsService;
import com.qg.service.QgGoodsTempStockService;
import com.qg.service.QgOrderService;
import com.qg.util.AlipayConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class AlipayController {

    @Reference(version = "1.0.0")
    private QgOrderService qgOrderService;
    @Reference(version = "1.0.0")
    private QgGoodsService qgGoodsService;
    @Reference(version = "1.0.0")
    private QgGoodsTempStockService qgGoodsTempStockService;

    //发起付款请求到支付宝，支付宝返回一个自动提交的表单页面代码，将该代码直接输出到浏览器
    @RequestMapping(value="/api/v/toPay")
    @ResponseBody
    public String pay(String orderId,String token,HttpServletRequest request) throws Exception{
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json",
                AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);//支付完成后，会员浏览器进行页面跳转的url/同步调用的url
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);//支付完成后，供支付宝服务器post方式restful远程调用的url
        //从数据库获取到订单的相关信息,发起支付
       QgOrder order = qgOrderService.getQgOrderById(orderId);
       QgGoods qgGoods = qgGoodsService.getQgGoodsById(order.getGoodsId());
        Map map = new HashMap();
        map.put("out_trade_no",order.getOrderNo());
        map.put("total_amount",order.getAmount());
        map.put("subject",qgGoods.getGoodsName());
        map.put("product_code","FAST_INSTANT_TRADE_PAY");

        alipayRequest.setBizContent(JSONArray.toJSONString(map));
        //请求
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    //查询支付结果
    @PostMapping("/query")
    @ResponseBody
    public String query(HttpServletRequest request) throws Exception{
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        //String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"}");
        //alipayRequest.setBizContent("{\"trade_no\":\""+ trade_no +"\"}");

        return alipayClient.execute(alipayRequest).getBody();
    }

    @RequestMapping("/return")
    public String returnUrl(HttpServletRequest request) throws Exception{
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
//        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
        //——请在这里编写您的程序（以下代码仅作参考）——
//        if("TRADE_SUCCESS".equals(trade_status)) {
            //重定向到同步页面
            return "redirect:http://localhost/paySuccess.html";
//            //商户订单号
//            String out_trade_no = request.getParameter("out_trade_no");
//            //支付宝交易号
//            String trade_no = request.getParameter("trade_no");
//            //付款金额
//            String total_amount = request.getParameter("total_amount");
//            return ("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
//        }else {
//            return "redirect:http://localhost/payFail.html";
//        }
    }


    @PostMapping("/notify")
    @ResponseBody
    public String notifyUrl(HttpServletRequest request) throws Exception {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

//        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
//        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //TODO: 更新本地订单的状态，支付交易号
                System.out.println("支付成功，更新本地订单的状态，支付交易号");
                Map map = new HashMap();
                map.put("orderNo",out_trade_no);
                List<QgOrder> orders =qgOrderService.getQgOrderListByMap(map);
                if(orders.size()==0){
                    return "fail";
                }
                QgOrder order =orders.get(0);
                order.setStatus(1);
                order.setAliTradeNo(trade_no);
                qgOrderService.qdtxModifyQgOrder(order);

                //TODO:更新GoodsTempStock的状态,更新商品表的库存-1
                QgGoodsTempStock tempStock = new QgGoodsTempStock();
                tempStock.setId(order.getStockId());
                tempStock.setStatus(1);
                qgGoodsTempStockService.qdtxModifyQgGoodsTempStock(tempStock);

                QgGoods goods = new QgGoods();
                goods.setId(Long.parseLong(order.getGoodsId()));
                goods.setStock(order.getNum());
                qgGoodsService.qdtxModifyQgGoods(goods);



                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            return "success";

//        }else {//验证失败
//            return "fail";

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
//        }

    }


}
