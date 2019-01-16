import java.util.List;

public class Collection {

    private InfoBean info;
    private List<?> variables;
    private List<ItemBean> item;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<?> getVariables() {
        return variables;
    }

    public void setVariables(List<?> variables) {
        this.variables = variables;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class InfoBean {

        private String name;
        private String _postman_id;
        private String description;
        private String schema;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String get_postman_id() {
            return _postman_id;
        }

        public void set_postman_id(String _postman_id) {
            this._postman_id = _postman_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }
    }

    public static class ItemBean {

        private String name;
        private RequestBean request;
        private List<?> response;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public RequestBean getRequest() {
            return request;
        }

        public void setRequest(RequestBean request) {
            this.request = request;
        }

        public List<?> getResponse() {
            return response;
        }

        public void setResponse(List<?> response) {
            this.response = response;
        }

        public static class RequestBean {
            private Object url;
            private String method;
            private BodyBean body;
            private String description;
            private List<?> header;

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public BodyBean getBody() {
                return body;
            }

            public void setBody(BodyBean body) {
                this.body = body;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<?> getHeader() {
                return header;
            }

            public void setHeader(List<?> header) {
                this.header = header;
            }

            public static class BodyBean {
                private String mode;
                private List<UrlencodedBean> urlencoded;
                private List<FormdataBean> formdata;

                public String getMode() {
                    return mode;
                }

                public void setMode(String mode) {
                    this.mode = mode;
                }

                public List<UrlencodedBean> getUrlencoded() {
                    return urlencoded;
                }

                public void setUrlencoded(List<UrlencodedBean> urlencoded) {
                    this.urlencoded = urlencoded;
                }

                public List<FormdataBean> getFormdata() {
                    return formdata;
                }

                public void setFormdata(List<FormdataBean> formdata) {
                    this.formdata = formdata;
                }

                public static class UrlencodedBean {
                    private String key;
                    private String value;
                    private String description;
                    private String type;

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }

                public static class FormdataBean {
                    private String key;
                    private String value;
                    private String type;

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }
        }
    }
}
