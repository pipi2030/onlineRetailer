<template xmlns:v-touch="http://www.w3.org/1999/xhtml">
    <div class="container" v-touch:right="rightSlide" v-touch:left="leftSlide">
        <!--轮播图-->
        <van-swipe :autoplay="3000">
            <van-swipe-item v-for="(slide, index) in slideList" :key="index">
                <img :src="slideImagesURL + slide.imageName" width="100%" height="400px"/>
            </van-swipe-item>
        </van-swipe>

        <!--主体-->
        <!--part1 商品类型-->
        <van-divider :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px',fontSize:'25px' }" dashed>
            商品类型
        </van-divider>
        <van-grid :border="false" :gutter="10">
            <van-grid-item v-for="productType in productTypeList">
                <div @click="toProduct(productType.typeId,productType.typeName)" align="center">
                    <van-image :src="productTypeURL + productType.typeImage" width="100px" height="100px" radius=10 />
                    <span>{{productType.typeName}}</span>
                </div>
            </van-grid-item>
        </van-grid>

        <!--part1 推荐商品-->
        <van-divider :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px',fontSize:'25px' }" dashed>
            推荐商品
        </van-divider>
        <van-grid :border="false" :gutter="10"  :column-num="2">
            <van-grid-item v-for="product in recommendList">
                <div @click="toProductDetail(product.productId)" align="center">
                    <van-image :src="product.productImage" width="240px" height="350px" radius=10 />
                    <div class="van-multi-ellipsis--l2">{{product.productName}}</div>
                    <div class="tag">推荐</div>
                    <div class="red-price"><small>￥</small><span class="product-price">{{product.productPrice}}</span></div>
                </div>
            </van-grid-item>
        </van-grid>

        <!--part2 热销商品-->
        <van-divider :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px',fontSize:'25px' }" dashed>
            热销商品
        </van-divider>
        <van-grid :border="false" :gutter="10" :column-num="2">
            <van-grid-item v-for="product in topList">
                <div @click="toProductDetail(product.productId)" align="center">
                    <van-image :src="product.productImage" width="240px" height="350px" radius=10 />
                    <div class="van-multi-ellipsis--l2">{{product.productName}}</div>
                    <div class="tag">&nbsp;热销&nbsp;</div>
                    <div class="red-price"><small>￥</small><span class="product-price">{{product.productPrice}}</span></div>
                </div>
            </van-grid-item>
        </van-grid>

        <!--part3 新出商品-->
        <van-divider :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px',fontSize:'25px' }" dashed>
            新出商品
        </van-divider>
        <van-grid :border="false" :gutter="10" :column-num="2">
            <van-grid-item v-for="product in newList">
                <div @click="toProductDetail(product.productId)" align="center">
                    <van-image :src="product.productImage" width="240px" height="350px" radius=10 />
                    <div class="van-multi-ellipsis--l2">{{product.productName}}</div>
                    <div class="tag">&nbsp;新品&nbsp;</div>
                    <div class="red-price"><small>￥</small><span class="product-price">{{product.productPrice}}</span></div>
                </div>
            </van-grid-item>
        </van-grid>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data(){
            return{
                slideImagesName:['1.jpg','2.jpg','3.jpeg','4.jpg','5.jpg'],
                slideImagesURL: this.$store.state.slideImagesURL,
                productTypeURL: this.$store.state.productTypeURL,
                productURL:this.$store.state.productURL,
                productTypeList:[],
                recommendList:[],
                topList:[],
                newList:[],
                slideList:[],
            }
        },
        methods:{
            getProductTypeList(){
                this.$http.get("/api/productType/list").then(
                    res =>{
                        if(res.data.status === 1){
                            this.productTypeList = res.data.data.productTypeList;
                        }else {
                            this.$toast(res.data.data.message)
                        }
                    }
                ).catch(error=>{
                    console.log(error)
                })
            },
            getRecommendList(){
                this.$http.get("/api/product/selectRecommendList").then(
                    res =>{
                        if(res.data.status === 1){
                            this.recommendList = res.data.data.RecommendProductList;
                        }else {
                            this.$toast(res.data.data.message)
                        }
                    }
                ).catch(error=>{
                    console.log(error)
                })
            },
            getTopList(){
                this.$http.get("/api/product/selectTopList").then(
                    res =>{
                        if(res.data.status === 1){
                            this.topList = res.data.data.TopProductList;
                        }else {
                            this.$toast(res.data.data.message)
                        }
                    }
                ).catch(error=>{
                    console.log(error)
                })
            },
            getNewList(){
                this.$http.get("/api/product/selectNewList").then(
                    res =>{
                        if(res.data.status === 1){
                            this.newList = res.data.data.NewProductList;
                        }else {
                            this.$toast(res.data.data.message)
                        }
                    }
                ).catch(error=>{
                    console.log(error)
                })
            },
            //进入商品页
            toProduct(typeId,typeName){
                //router跳转 route取值
                //push传参
                this.$router.push({
                    path:"/product",
                    query:{
                        typeId: typeId,
                        typeName:typeName,
                    }
                });
            },
            //进入商品详情页
            toProductDetail(productId){
                this.$router.push({path: "/detail",query: {productId}});
            },
            getSlide(){
                this.$http.get("/api/getSlide").then(res=>{
                        if(res.data.status === 1){
                            this.slideList = res.data.data.slideList;
                        }else{
                            console.log(res.data.message);
                        }
                }).catch(()=>{})
            },
            rightSlide(){
                this.$router.go(1)
            },
            leftSlide(){
                this.$router.go(-1)
            }
        },
        created(){
            this.getProductTypeList();
            this.getRecommendList();
            this.getTopList();
            this.getNewList();
            this.getSlide();
        },
        mounted(){
        }
    }
</script>

<style scoped>

</style>