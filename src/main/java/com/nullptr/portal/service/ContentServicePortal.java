package com.nullptr.portal.service;


/**
 * Created by Nullptr on 2017/5/22.
 */
public interface ContentServicePortal {

    /**
     * 获取大广告位的内容，轮播展示商品
     * 通过调用Rest发布的服务得到一个jeson的字符串。
     *
     * @return
     */
    public String getAd1List();
}
