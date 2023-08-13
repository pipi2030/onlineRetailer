<template xmlns:v-touch="http://www.w3.org/1999/xhtml">
    <div class="container" v-touch:right="rightSlide" v-touch:left="leftSlide">
        <!--搜索框-->
        <van-search
                v-model="productName"
                shape="round"
                background="#ff5a02"
                left-icon=""
                placeholder="请输入商品名称"
                show-action
                @search="search">
            <template #action>
                <van-button color="linear-gradient(to bottom right, #ff6034, #ee0a24)"  @click="search">搜索</van-button>
            </template>
        </van-search>

        <!--搜索结果-->
        <van-grid :border="false" :gutter="10" :column-num="2">
            <van-grid-item v-for="product in productList">
                <div @click="toProductDetail(product.productId)">
                    <van-image :src="product.productImage" width="200px" height="230px" radius=10 />
                    <div v-html="product.productName" class="van-multi-ellipsis--l2"></div>
                    <div class="red-price"><small>￥</small><span class="product-price">{{product.productPrice}}</span></div>
                </div>
            </van-grid-item>
        </van-grid>

        <!--分页-->
        <van-pagination v-model="currentPage" :total-items="pageTotal" :show-page-size="pageSize" @change="getProductPage">
            <template #prev-text>
                <van-icon name="arrow-left" />
            </template>
            <template #next-text>
                <van-icon name="arrow" />
            </template>
            <template #page="{ text }">{{ text }}</template>
        </van-pagination>
    </div>

</template>

<script>
    import Cookies from 'js-cookie'
    export default {
        name: "SearchView",
        data(){
            return{
                productName:'',
                productList:[],
                productPage:{},
                currentPage:1,
                pageSize:0,
                pageTotal:0,
            }
        },
        methods:{
            search(){
                this.currentPage = 1;
                this.getProductPage();
                // Cookies.set("productName",this.productName);
            },
            strongTheKeyword(){
                if(this.productName !=''){
                    this.productList.forEach(product=>{
                        //new RegExp(this.productName,'g') 是为了实现全局替换
                        product.productName = product.productName.replace(new RegExp(this.productName,'g'),"<span style='color: darkred'>" + this.productName + "</span>")
                    })
                }
            },
            getProductPage(){
                this.$http.get("/api/product/getProductPage?productName=" + this.productName + "&currentPage=" + this.currentPage
                ).then(
                    res=>{
                        let productPage = res.data.data.ProductPage;
                        this.productPage = productPage;
                        this.productList = productPage.records;
                        this.pageSize = productPage.size;
                        this.pageTotal = productPage.total;
                    }
                ).catch(error=>{
                    console.log(error)
                })
            },
            //进入商品详情页
            toProductDetail(productId){
                this.$router.push({path: "/detail",query: {productId}});
            },
            rightSlide(){
                this.$router.go(1)
            },
            leftSlide(){
                this.$router.go(-1)
            }
        },
        created(){
            this.getProductPage()
        }
    }
</script>

<style scoped>

</style>