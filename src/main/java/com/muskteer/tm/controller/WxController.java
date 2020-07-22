package com.muskteer.tm.controller;

import com.muskteer.tm.ai.AiService;
import com.muskteer.tm.common.bean.ArticleInfoBean;
import com.muskteer.tm.common.bean.WeixinMessageRequestBean;
import com.muskteer.tm.common.bean.WeixinMessageResponseBean;
import com.muskteer.tm.service.ArticleService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 */
@RestController
@RequestMapping({"/wx/*"})
public class WxController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AiService aiService;

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response) {
        String timestamp, nonce, echostr, signature;
        timestamp = request.getParameter("timestamp");
        nonce = request.getParameter("nonce");
        echostr = request.getParameter("echostr");
        signature = request.getParameter("signature");

        System.out.println(request.getParameterMap());
        return  echostr;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Object msg(@RequestBody WeixinMessageRequestBean msg, HttpServletResponse response) {
        System.out.println("有人发消息了啊" + ReflectionToStringBuilder.toString(msg));
        //创建消息响应对象
        WeixinMessageResponseBean rsp = new WeixinMessageResponseBean();
        //把原来的发送方设置为接收方
        rsp.setToUserName(msg.getFromUserName());
        //把原来的接收方设置为发送方
        rsp.setFromUserName(msg.getToUserName());
        //获取接收的消息类型
        String msgType = msg.getMsgType();
        //设置消息的响应类型
        rsp.setMsgType(msgType);
        //设置消息创建时间
        rsp.setCreateTime(new Date().getTime());
        //根据类型设置不同的消息数据
        if("text".equals(msgType)){
            ai(msg.getContent(), rsp);
        }else if("image".equals(msgType)){
            rsp.setMediaId(new String[]{msg.getMediaId()});
        }else if ("voice".equals(msgType)){
            rsp.setMsgType("text");
            ai(msg.getRecognition().trim(), rsp);
        }
        System.out.println("回了消息" + ReflectionToStringBuilder.toString(rsp));
        return rsp;
    }

    public void ai(String msg, WeixinMessageResponseBean rsp){
        List<ArticleInfoBean> list = articleService.search(msg.trim());
        if (list == null || list.size()==0){
            rsp.setContent(aiService.reply(msg.trim()));
        }else {
            String url = "https://talkmovie.cn/article/" +
                    new SimpleDateFormat("yyyyMM").format(list.get(0).getTougaoTime())
                    + "/" + list.get(0).getArticleID() + ".html";
            rsp.setContent(url);
        }
    }
}