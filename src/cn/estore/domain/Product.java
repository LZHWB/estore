    package cn.estore.domain;

    public class Product {
        private int id; // 商品编号
        private String name; // 名称
        private double price; // 价格
        private int pnum; // 数量
        private int c3code; //编码
        private String imgurl; // 图片路径
        private String description; // 描述
        private String color;
        private int totalSaleNum; // 总销售数量

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }


        public int getC3code() {
            return c3code;
        }

        public void setC3code(int c3code) {
            this.c3code = c3code;
        }

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getTotalSaleNum() {
            return totalSaleNum;
        }

        public void setTotalSaleNum(int totalSaleNum) {
            this.totalSaleNum = totalSaleNum;
        }


        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Product() {
        }

    }
