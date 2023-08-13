<template xmlns:v-touch="http://www.w3.org/1999/xhtml">
    <div  v-touch:right="rightSlide" v-touch:left="leftSlide">
        <!--<h1>{{typeName}}</h1>-->
        <van-grid :border="false" :gutter="10"  :column-num="2">
            <van-grid-item v-for="product in productList">
                <div  @click="toProductDetail(product.productId)">
                    <van-image :src="product.productImage" width="240px" height="350px" radius=10 />
                    <div class="van-multi-ellipsis--l2">{{product.productName}}</div>
                    <div class="red-price"><small>￥</small><span class="product-price">{{product.productPrice}}</span></div>
                </div>
            </van-grid-item>
        </van-grid>
        <!--分页-->
        <van-pagination v-model="currentPage" :total-items="pageTotal" :show-page-size="pageSize" @change="getProductList">
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
    export default {
        name: "ProductView",
        data(){
          return{
              typeId:this.$route.query.typeId,
              typeName:this.$route.query.typeName,
              productList:[],
              currentPage:1,
              pageSize:0,
              pageTotal:0,
          }
        },
        methods:{
            getProductList(){
                this.$http.get("/api/product/selectTypeList/" + this.typeId + "?currentPage=" + this.currentPage)
                    .then(res=>{
                        let productList = res.data.data.ProductList;
                        this.productList = productList.records;
                        this.pageSize = productList.size;
                        this.pageTotal = productList.total;
                    })
                    .catch(e=>{
                        console.log(e);
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
            this.getProductList();
        }
    }
</script>

<style scoped>
    h1{
        text-align: center;
    }
</style>