<template>
    <div class="container detail" v-touch:right="rightSlide" v-touch:left="leftSlide">
        <!--商品大图-->
        <van-image
                width="100%"
                height="600px"
                :src="product.productImage"
        />

        <div class="body">
            <!--商品详细信息-->
            <div class="white-container">
                <van-row>
                    <van-col>
                        <div class="tag">{{product.productType}}</div>
                    </van-col>
                    <van-col span="4">
                        <div class="red-price"><small>￥</small><span class="product-price">{{product.productPrice}}</span></div>
                    </van-col>
                </van-row>
                <div class="van-multi-ellipsis--l2 product-name">{{product.productName}}</div>
                <div class="small">已售:{{product.sellCount}}&nbsp;库存剩余{{product.productStock}}件&nbsp;上架时间:{{productCreatime}}</div>
                <van-row type="flex">
                    <van-col span="6"><van-stepper v-model="purchaseCount" theme="round" input-width="50px" button-size="28px" disable-input /></van-col>
                    <van-col span="5"><van-button type="danger"><span class="h3">购买</span></van-button></van-col>
                </van-row>
            </div>
            <!--评价列表-->
            <div class="white-container">
                <div class="commentTop">
                    <van-row type="flex" justify="space-between">
                        <van-col span="7">宝贝评价({{commentCount}})</van-col>
                        <van-col span="4"><van-icon name="arrow" @click="openCommentList" /></van-col>
                    </van-row>
                </div>
                <div v-for="comment in commentList">
                    <div class="commentBody">
                        <van-row type="flex">
                            <van-col span="2">
                                <van-image
                                        round
                                        width="30px"
                                        height="30px"
                                        :src="$store.state.headImageURL + comment.userImage"
                                />
                            </van-col>
                            <van-col span="5">
                                <div class="user-name">
                                    {{comment.userName}}
                                </div>
                            </van-col>
                            <van-col span="7">
                                <van-rate
                                        v-model="comment.score"
                                        :size="25"
                                        color="#ffd21e"
                                        void-icon="star"
                                        void-color="#eee"
                                        readonly
                                />
                            </van-col>
                            <div class="small commentTime">{{comment.commentTime}}</div>
                        </van-row>
                        {{comment.content}}
                    </div>
                </div>
            </div>
            <!--发布评价-->
            <div class="white-container">
                <van-row>
                    <van-col span="16" offset="4">
                        <van-form @submit="commitComment">
                            <h3 class="h3">发表评论</h3>
                            <van-field
                                    v-model="newCommentContent"
                                    rows="5"
                                    autosize
                                    label=""
                                    type="textarea"
                                    maxlength="200"
                                    placeholder="请留下您宝贵的评论"
                                    show-word-limit
                            />
                            <van-rate
                                    v-model="newRate"
                                    :size="30"
                                    color="#ffd21e"
                                    void-icon="star"
                                    void-color="#eee"
                            />
                            <div style="margin: 16px;">
                                <van-button round block type="info" native-type="submit">提交</van-button>
                            </div>
                        </van-form>
                    </van-col>
                </van-row>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "DetailView",
        data(){
            return{
                productId:this.$route.query.productId,
                product:{},
                productCreatime:'',
                purchaseCount:1,
                commentList:[],
                commentCount:0,
                //用户即将提交的评论
                newCommentContent: '',
                //用户即将提交的评分
                newRate:0,
            }
        },
        methods:{
            // 给后端发送日期格式
            formatDateValue(now) {
                var year = this.dateZero(now.getFullYear()); //取得4位数的年份
                var month = this.dateZero(now.getMonth() + 1); //取得日期中的月份，其中0表示1月，11表示12月
                var date = this.dateZero(now.getDate()); //返回日期月份中的天数（1到31）
                var hour = this.dateZero(now.getHours()); //返回日期中的小时数（0到23）
                var minute = this.dateZero(now.getMinutes()); //返回日期中的分钟数（0到59）
                var second = this.dateZero(now.getSeconds()); //返回日期中的秒数（0到59）
                return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
            },
            // 日期前面加0
            dateZero(time) {
                if (time < 10) {
                    time = "" + "0" + time;
                }
                return time;
            },
            getProductVo(){
                this.$http.get("/api/product/getProductDetail/" + this.productId).then(res=>{
                    if(res.data.status === 1){
                        this.product = res.data.data.productVo;
                        this.productCreatime = res.data.data.productVo.createTime.split(" ")[0]
                    }else{
                        this.$router.go(-1);
                    }
                })
            },
            getCommentList(){
                this.$http.get("/api/comment/getCommentList/" + this.productId).then(res=>{
                    if(res.data.status === 1){
                        this.commentList = res.data.data.commentList;
                        this.commentCount = res.data.data.commentList.length;
                    }
                }).catch(()=>{
                    console.log("可能没有开启后端服务器");
                })
            },
            commitComment(){
                let time = new Date();
                this.$http.post(
                    '/api/comment/addComment', {
                        productId: this.$route.query.productId,
                        userId: 1,
                        content: this.newCommentContent,
                        score: this.newRate
                    }
                ).then(res=>{
                    if(res.data.insert === 1){
                        this.$notify({ type: 'success', message: '评论成功' });
                    }else{
                        this.$notify({ type: 'warning', message: '评论成功' });
                    }
                    this.newCommentContent = "";
                    this.newRate = 0 ;
                    //评论成功后重新获取评论列表
                    this.getCommentList();
                }).catch(error=>{
                    console.log(error);
                })
            },
            openCommentList(){},
            rightSlide(){
                this.$router.go(1)
            },
            leftSlide(){
                this.$router.go(-1)
            },
        },
        created(){
            this.getProductVo();
            this.getCommentList();
        },
        mounted(){
        }
    }
</script>

<style scoped>
    .h3{
        font-size: 30px;
        text-align: center;
    }
    .commentTop{
        margin-bottom: 20px;
    }
    .commentBody{
        margin: 30px 0px;
    }
    .body{
        margin:0px 20px;
    }
    .test{
        border: 1px solid red;
    }
    .detail{
        background-color: #F2F2F2;
    }
    .user-name{
        text-align: left;
    }
    .commentTime{
        text-align: left;
        height: 10px;
    }
</style>