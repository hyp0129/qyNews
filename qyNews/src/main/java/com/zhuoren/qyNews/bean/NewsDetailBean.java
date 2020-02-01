package com.zhuoren.qyNews.bean;
import java.io.Serializable;
import java.util.List;

public class NewsDetailBean implements Serializable {
    @Override
    public String toString() {
        return "NewsDetailBean{" +
                ", body=" + body +
                '}';
    }

    private BodyBean body;
    public BodyBean getBody() {
        return body;
    }
    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        @Override
        public String toString() {
            return "BodyBean{" +
                    "aid='" + aid + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", source='" + source + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", text='" + text + '\'' +
                    ", subscribe=" + subscribe +
                    ", slides=" + slides +
                    ", img=" + img +
                    '}';
        }
        private String aid;
        private String text;
        private String id;
        private String title;
        private String author;
        private String editorcode;
        private String source;
        private String updateTime;
        private SubscribeBean subscribe;
        private List<SlidesBean> slides;
        private List<ImgBean> img;
        public String getEditorcode() {
            return editorcode;
        }
        public void setEditorcode(String editorcode) {
            this.editorcode = editorcode;
        }
        public List<SlidesBean> getSlides() {
            return slides;
        }
        public void setSlides(List<SlidesBean> slides) {
            this.slides = slides;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getAid() {
            return aid;
        }
        public void setAid(String aid) {
            this.aid = aid;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getAuthor() {
            return author;
        }
        public void setAuthor(String author) {
            this.author = author;
        }
        public String getSource() {
            return source;
        }
        public void setSource(String source) {
            this.source = source;
        }
        public String getUpdateTime() {
            return updateTime;
        }
        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
        public SubscribeBean getSubscribe() {
            return subscribe;
        }
        public void setSubscribe(SubscribeBean subscribe) {
            this.subscribe = subscribe;
        }
        public List<ImgBean> getImg() {
            return img;
        }
        public void setImg(List<ImgBean> img) {
            this.img = img;
        }


        public static class SlidesBean implements Serializable {
            @Override
            public String toString() {
                return "SlidesBean{" +
                        "image='" + image + '\'' +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }

            private String image;
            private String title;
            private String description;
            public String getImage() {
                return image;
            }
            public void setImage(String image) {
                this.image = image;
            }
            public String getTitle() {
                return title;
            }
            public void setTitle(String title) {
                this.title = title;
            }
            public String getDescription() {
                return description;
            }
            public void setDescription(String description) {
                this.description = description;
            }
        }



        public static class SubscribeBean {
            private String type;
            private String cateSource;
            private String catename;
            private String logo;
            public String getType() {
                return type;
            }
            public void setType(String type) {
                this.type = type;
            }
            public String getCateSource() {
                return cateSource;
            }
            public void setCateSource(String cateSource) {
                this.cateSource = cateSource;
            }
            public String getCatename() {
                return catename;
            }
            public void setCatename(String catename) {
                this.catename = catename;
            }
            public String getLogo() {
                return logo;
            }
            public void setLogo(String logo) {
                this.logo = logo;
            }
        }


        public static class ImgBean {
            private String url;
            private SizeBean size;
            public String getUrl() {
                return url;
            }
            public void setUrl(String url) {
                this.url = url;
            }
            public SizeBean getSize() {
                return size;
            }
            public void setSize(SizeBean size) {
                this.size = size;
            }


            public static class SizeBean {
                /**
                 * width : 450
                 * height : 250
                 */
                private String width;
                private String height;
                public String getWidth() {
                    return width;
                }
                public void setWidth(String width) {
                    this.width = width;
                }
                public String getHeight() {
                    return height;
                }
                public void setHeight(String height) {
                    this.height = height;
                }
            }
        }
    }
}
