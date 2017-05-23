package com.nullptr.portal.service.impl;

import com.nullptr.common.pojo.TaotaoResult;
import com.nullptr.common.utils.HttpClientUtil;
import com.nullptr.common.utils.JsonUtils;
import com.nullptr.pojo.TbContent;
import com.nullptr.portal.pojo.AdNode;
import com.nullptr.portal.service.ContentServicePortal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nullptr on 2017/5/22.
 */
@Service
public class ContentServicePortalImpl implements ContentServicePortal {

    //@Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    //@Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;

    //@Value("${REST_CONTENT_AD1_CID}")
    private String REST_CONTENT_AD1_CID;

    /**
     * 获取大广告位的内容，轮播展示商品
     * 通过调用Rest发布的服务得到一个jeson的字符串。
     *
     * @return
     */
    @Override
    public String getAd1List() {

        //调用服务获得数据
        System.out.println(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD1_CID);
        //String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD1_CID);
        //http://localhost:8081/rest
        String json = HttpClientUtil.doGet("http://localhost:8081/rest/content/89");
        //通过TaoTaoResult转换返回的json数据为包含元素TbContent的List
        TaotaoResult taotaoResult = TaotaoResult.formatToList(json, TbContent.class);

        //取出Result中的data属性
        List<TbContent> tbContentList = (List<TbContent>) taotaoResult.getData();

        //把内容列表转换成AdNode列表
        List<AdNode> adNodes = new ArrayList<>();

        for (TbContent tbContent : tbContentList) {
            AdNode adNode = new AdNode();
            adNode.setHeight(240);
            adNode.setWidth(670);
            adNode.setSrc(tbContent.getPic());

            adNode.setHeightB(240);
            adNode.setWidthB(550);
            adNode.setSrcB(tbContent.getPic2());

            adNode.setAlt(tbContent.getSubTitle());
            adNode.setHref(tbContent.getUrl());

            adNodes.add(adNode);

        }

        String resultJson = JsonUtils.objectToJson(adNodes);
        return resultJson;
    }
}
