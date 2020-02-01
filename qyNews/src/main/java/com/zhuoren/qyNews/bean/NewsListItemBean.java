package com.zhuoren.qyNews.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class NewsListItemBean implements Serializable {
    @Override
    public String toString() {
        return "NewsListItemBean{" +
                ", type='" + type + '\'' +
                ", item=" + item +
                '}';
    }

    /**
     * listId : SYLB10NEW_DOWN
     * type : list
     * expiredTime : 180000
     * currentPage : 1
     * totalPage : 1
     * topsize : 0
     * item:
     */
    private String type;
    private List<ItemBean> item;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<ItemBean> getItem() {
        return item;
    }
    public void setItem(List<ItemBean> item) {
        this.item = item;
    }
    public static class ItemBean implements Serializable, MultiItemEntity {

        //图片类型
        public static final int TYPE_SLIDE = 4;

        //显示形式单图
        public static final int TYPE_DOC_TITLEIMG = 6;
        //显示形式多图
        public static final int TYPE_DOC_SLIDEIMG = 7;
        @Override
        public String toString() {
            return "ItemBean{" +
                    "type='" + type + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", title='" + title + '\'' +
                    ", source='" + source + '\'' +
                    ", subscribe=" + subscribe +
                    ", id='" + id + '\'' +
                    ", documentId='" + documentId + '\'' +
                    ", style=" + style +
                    ", commentsall='" + commentsall + '\'' +
                    ", link=" + link +
                    '}';
        }
        @Override
        public int getItemType() {
            return itemType;
        }

        public int itemType;
        private String type;
        private String thumbnail;
        private String title;
        private String source;
        private SubscribeBean subscribe;
        private String id;
        private String documentId;
        private StyleBean style;
        private String commentsall;
        private LinkBean link;

        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }

        public String getThumbnail() {
            return thumbnail;
        }
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getSource() {
            return source;
        }
        public void setSource(String source) {
            this.source = source;
        }

        public SubscribeBean getSubscribe() {
            return subscribe;
        }
        public void setSubscribe(SubscribeBean subscribe) {
            this.subscribe = subscribe;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        public String getDocumentId() {
            return documentId;
        }
        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public StyleBean getStyle() {
            return style;
        }
        public void setStyle(StyleBean style) {
            this.style = style;
        }

        public String getCommentsall() {
            return commentsall;
        }
        public void setCommentsall(String commentsall) {
            this.commentsall = commentsall;
        }

        public LinkBean getLink() {
            return link;
        }
        public void setLink(LinkBean link) {
            this.link = link;
        }

        public static class SubscribeBean {
            @Override
            public String toString() {
                return "SubscribeBean{" +
                        ", type='" + type + '\'' +
                        '}';
            }

            /**
             * cateid : 央视网
             * type : source
             * catename : 央视网
             * description :
             */
            private String type;
            public String getType() {
                return type;
            }
            public void setType(String type) {
                this.type = type;
            }

        }

        public static class StyleBean {
            @Override
            public String toString() {
                return "StyleBean{" +
                        "view='" + view + '\'' +
                        ", backreason=" + backreason +
                        ", images=" + images +
                        '}';
            }

            /**
             * backreason : ["来源:央视网","内容质量差","旧闻、重复","标题党"]
             * view : titleimg
             */
            private String view;
            private List<String> backreason;
            private List<String> images;
            public List<String> getImages() {
                return images;
            }
            public void setImages(List<String> images) {
                this.images = images;
            }
            public String getView() {
                return view;
            }
            public void setView(String view) {
                this.view = view;
            }
            public List<String> getBackreason() {
                return backreason;
            }
            public void setBackreason(List<String> backreason) {
                this.backreason = backreason;
            }
        }

        public static class LinkBean {
            /**
             * type : doc
             * url : http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470051364453&channelKey=Y21wcF8xNzAwN183MTlfNzU=&channelid=SYLB10NEW_DOWN
             * weburl : http://share.iclient.ifeng.com/sharenews.f?aid=034470051364453
             */
            private String type;
            private String url;
            private String weburl;

            @Override
            public String toString() {
                return "LinkBean{" +
                        "type='" + type + '\'' +
                        ", url='" + url + '\'' +
                        ", weburl='" + weburl + '\'' +
                        '}';
            }
            public String getType() {
                return type;
            }
            public void setType(String type) {
                this.type = type;
            }
            public String getUrl() {
                return url;
            }
            public void setUrl(String url) {
                this.url = url;
            }
            public String getWeburl() {
                return weburl;
            }
            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }
        }
    }
}
