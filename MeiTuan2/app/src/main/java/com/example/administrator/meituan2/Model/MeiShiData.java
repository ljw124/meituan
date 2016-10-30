package com.example.administrator.meituan2.Model;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public class MeiShiData {

    /**
     * count : 17691
     */

    private PagingBean paging;
    /**
     * poi : {"poiid":"6626982","channel":"food","name":"阿新庆丰包子铺(铭功路店)","frontImg":"http://p0.meituan.net/w.h/deal/28b010708de8d7192fbdeb49abbc9eb9194305.jpg","avgPrice":10,"avgScore":3.9,"cateName":"快餐","areaName":"万象城","lat":34.754176,"lng":113.660165,"iUrl":"","isWaimai":0,"characteristicArea":{},"payAbstracts":[{"type":"promotions","abstract":"每满10减1（买单立享）","icon_url":"http://p0.meituan.net/tuanpic/discount.png"},{"type":"group","abstract":"6.8元庆丰包子单人餐，8.9元单人餐，12.8元庆丰包子2人餐，17.9元包子，18.9元炸酱面套餐，19.9元馄饨套餐，28.8元豪华双人餐","icon_url":"http://p1.meituan.net/tuanpic/group_ia_tuan_01.png"},{"type":"coupon","abstract":"8.9代10元","icon_url":"http://p1.meituan.net/tuanpic/group_ia_quan_01.png"}],"extra":{"icons":["http://p1.meituan.net/tuanpic/maidanicon.png"]},"campaignTag":"多优惠+","extCampaign":{},"recommendation":{"tagId":-1,"content":""}}
     */

    private List<DataBean> data;
    /**
     * position : 3
     * tipmsgs : [{"type":7,"valueId":0,"parentId":0,"name":"不出二品","strategy":"216307913136231680%2b%e4%b8%8d%e5%87%ba%e4%ba%8c%e5%93%81","iUrl":"imeituan://www.meituan.com/search?q=%e4%b8%8d%e5%87%ba%e4%ba%8c%e5%93%81&push=false&ste=_b400011","iUrlType":"search","selectMsg":{}},{"type":7,"valueId":0,"parentId":0,"name":"黄焖鸡米饭","strategy":"216307913136231680%2b%e9%bb%84%e7%84%96%e9%b8%a1%e7%b1%b3%e9%a5%ad","iUrl":"imeituan://www.meituan.com/search?q=%e9%bb%84%e7%84%96%e9%b8%a1%e7%b1%b3%e9%a5%ad&push=false&ste=_b400011","iUrlType":"search","selectMsg":{}},{"type":7,"valueId":0,"parentId":0,"name":"日出茶太","strategy":"216307913136231680%2b%e6%97%a5%e5%87%ba%e8%8c%b6%e5%a4%aa","iUrl":"imeituan://www.meituan.com/search?q=%e6%97%a5%e5%87%ba%e8%8c%b6%e5%a4%aa&push=false&ste=_b400011","iUrlType":"search","selectMsg":{}},{"type":0,"valueId":17,"parentId":1,"name":"火锅","strategy":"1657459778088657408%2b%e7%81%ab%e9%94%85","iUrl":"imeituan://www.meituan.com/deal/list","iUrlType":"sieve","selectMsg":{}},{"type":0,"valueId":36,"parentId":1,"name":"小吃快餐","strategy":"1657459778088657408%2b%e5%b0%8f%e5%90%83%e5%bf%ab%e9%a4%90","iUrl":"imeituan://www.meituan.com/deal/list","iUrlType":"sieve","selectMsg":{}},{"type":0,"valueId":11,"parentId":1,"name":"甜点饮品","strategy":"1657459778088657408%2b%e7%94%9c%e7%82%b9%e9%a5%ae%e5%93%81","iUrl":"imeituan://www.meituan.com/deal/list","iUrlType":"sieve","selectMsg":{}},{"type":0,"valueId":20097,"parentId":1,"name":"生日蛋糕","strategy":"1657459778088657408%2b%e7%94%9f%e6%97%a5%e8%9b%8b%e7%b3%95","iUrl":"imeituan://www.meituan.com/deal/list","iUrlType":"sieve","selectMsg":{}},{"type":2,"valueId":0,"parentId":0,"name":"午餐","strategy":"1945690154254899968%2b%e5%8d%88%e9%a4%90","iUrl":"imeituan://www.meituan.com/deal/list","iUrlType":"sieve","selectMsg":{"selectKey":"poi_attr_20043","selectValue":"20123"}}]
     */

    private List<TipsBean> tips;
    /**
     * poiid : 6626982
     * ct_poi : 1657460192548751104_a6626982_c0_e3832144520567039616
     */

    private List<CtPoisBean> ct_pois;

    public PagingBean getPaging() {
        return paging;
    }

    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<TipsBean> getTips() {
        return tips;
    }

    public void setTips(List<TipsBean> tips) {
        this.tips = tips;
    }

    public List<CtPoisBean> getCt_pois() {
        return ct_pois;
    }

    public void setCt_pois(List<CtPoisBean> ct_pois) {
        this.ct_pois = ct_pois;
    }

    public static class PagingBean {
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static class DataBean {
        /**
         * poiid : 6626982
         * channel : food
         * name : 阿新庆丰包子铺(铭功路店)
         * frontImg : http://p0.meituan.net/w.h/deal/28b010708de8d7192fbdeb49abbc9eb9194305.jpg
         * avgPrice : 10.0
         * avgScore : 3.9
         * cateName : 快餐
         * areaName : 万象城
         * lat : 34.754176
         * lng : 113.660165
         * iUrl :
         * isWaimai : 0
         * characteristicArea : {}
         * payAbstracts : [{"type":"promotions","abstract":"每满10减1（买单立享）","icon_url":"http://p0.meituan.net/tuanpic/discount.png"},{"type":"group","abstract":"6.8元庆丰包子单人餐，8.9元单人餐，12.8元庆丰包子2人餐，17.9元包子，18.9元炸酱面套餐，19.9元馄饨套餐，28.8元豪华双人餐","icon_url":"http://p1.meituan.net/tuanpic/group_ia_tuan_01.png"},{"type":"coupon","abstract":"8.9代10元","icon_url":"http://p1.meituan.net/tuanpic/group_ia_quan_01.png"}]
         * extra : {"icons":["http://p1.meituan.net/tuanpic/maidanicon.png"]}
         * campaignTag : 多优惠+
         * extCampaign : {}
         * recommendation : {"tagId":-1,"content":""}
         */

        private PoiBean poi;

        public PoiBean getPoi() {
            return poi;
        }

        public void setPoi(PoiBean poi) {
            this.poi = poi;
        }

        public static class PoiBean {
            private String poiid;
            private String channel;
            private String name;
            private String frontImg;
            private double avgPrice;
            private double avgScore;
            private String cateName;
            private String areaName;
            private double lat;
            private double lng;
            private String iUrl;
            private int isWaimai;
            private CharacteristicAreaBean characteristicArea;
            private ExtraBean extra;
            private String campaignTag;
            private ExtCampaignBean extCampaign;
            /**
             * tagId : -1
             * content :
             */

            private RecommendationBean recommendation;
            /**
             * type : promotions
             * abstract : 每满10减1（买单立享）
             * icon_url : http://p0.meituan.net/tuanpic/discount.png
             */

            private List<PayAbstractsBean> payAbstracts;

            public String getPoiid() {
                return poiid;
            }

            public void setPoiid(String poiid) {
                this.poiid = poiid;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFrontImg() {
                return frontImg;
            }

            public void setFrontImg(String frontImg) {
                this.frontImg = frontImg;
            }

            public double getAvgPrice() {
                return avgPrice;
            }

            public void setAvgPrice(double avgPrice) {
                this.avgPrice = avgPrice;
            }

            public double getAvgScore() {
                return avgScore;
            }

            public void setAvgScore(double avgScore) {
                this.avgScore = avgScore;
            }

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getIUrl() {
                return iUrl;
            }

            public void setIUrl(String iUrl) {
                this.iUrl = iUrl;
            }

            public int getIsWaimai() {
                return isWaimai;
            }

            public void setIsWaimai(int isWaimai) {
                this.isWaimai = isWaimai;
            }

            public CharacteristicAreaBean getCharacteristicArea() {
                return characteristicArea;
            }

            public void setCharacteristicArea(CharacteristicAreaBean characteristicArea) {
                this.characteristicArea = characteristicArea;
            }

            public ExtraBean getExtra() {
                return extra;
            }

            public void setExtra(ExtraBean extra) {
                this.extra = extra;
            }

            public String getCampaignTag() {
                return campaignTag;
            }

            public void setCampaignTag(String campaignTag) {
                this.campaignTag = campaignTag;
            }

            public ExtCampaignBean getExtCampaign() {
                return extCampaign;
            }

            public void setExtCampaign(ExtCampaignBean extCampaign) {
                this.extCampaign = extCampaign;
            }

            public RecommendationBean getRecommendation() {
                return recommendation;
            }

            public void setRecommendation(RecommendationBean recommendation) {
                this.recommendation = recommendation;
            }

            public List<PayAbstractsBean> getPayAbstracts() {
                return payAbstracts;
            }

            public void setPayAbstracts(List<PayAbstractsBean> payAbstracts) {
                this.payAbstracts = payAbstracts;
            }

            public static class CharacteristicAreaBean {
            }

            public static class ExtraBean {
                private List<String> icons;

                public List<String> getIcons() {
                    return icons;
                }

                public void setIcons(List<String> icons) {
                    this.icons = icons;
                }
            }

            public static class ExtCampaignBean {
            }

            public static class RecommendationBean {
                private int tagId;
                private String content;

                public int getTagId() {
                    return tagId;
                }

                public void setTagId(int tagId) {
                    this.tagId = tagId;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }

            public static class PayAbstractsBean {
                private String type;
                // TODO: 2016/10/15  
                private String abstractX;
                private String icon_url;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getAbstractX() {
                    return abstractX;
                }

                public void setAbstractX(String abstractX) {
                    this.abstractX = abstractX;
                }

                public String getIcon_url() {
                    return icon_url;
                }

                public void setIcon_url(String icon_url) {
                    this.icon_url = icon_url;
                }
            }
        }
    }

    public static class TipsBean {
        private int position;
        /**
         * type : 7
         * valueId : 0
         * parentId : 0
         * name : 不出二品
         * strategy : 216307913136231680%2b%e4%b8%8d%e5%87%ba%e4%ba%8c%e5%93%81
         * iUrl : imeituan://www.meituan.com/search?q=%e4%b8%8d%e5%87%ba%e4%ba%8c%e5%93%81&push=false&ste=_b400011
         * iUrlType : search
         * selectMsg : {}
         */

        private List<TipmsgsBean> tipmsgs;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public List<TipmsgsBean> getTipmsgs() {
            return tipmsgs;
        }

        public void setTipmsgs(List<TipmsgsBean> tipmsgs) {
            this.tipmsgs = tipmsgs;
        }

        public static class TipmsgsBean {
            private int type;
            private int valueId;
            private int parentId;
            private String name;
            private String strategy;
            private String iUrl;
            private String iUrlType;
            private SelectMsgBean selectMsg;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getValueId() {
                return valueId;
            }

            public void setValueId(int valueId) {
                this.valueId = valueId;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStrategy() {
                return strategy;
            }

            public void setStrategy(String strategy) {
                this.strategy = strategy;
            }

            public String getIUrl() {
                return iUrl;
            }

            public void setIUrl(String iUrl) {
                this.iUrl = iUrl;
            }

            public String getIUrlType() {
                return iUrlType;
            }

            public void setIUrlType(String iUrlType) {
                this.iUrlType = iUrlType;
            }

            public SelectMsgBean getSelectMsg() {
                return selectMsg;
            }

            public void setSelectMsg(SelectMsgBean selectMsg) {
                this.selectMsg = selectMsg;
            }

            public static class SelectMsgBean {
            }
        }
    }

    public static class CtPoisBean {
        private int poiid;
        private String ct_poi;

        public int getPoiid() {
            return poiid;
        }

        public void setPoiid(int poiid) {
            this.poiid = poiid;
        }

        public String getCt_poi() {
            return ct_poi;
        }

        public void setCt_poi(String ct_poi) {
            this.ct_poi = ct_poi;
        }
    }
}
