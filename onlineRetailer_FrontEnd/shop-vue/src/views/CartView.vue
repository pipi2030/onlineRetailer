<template>
    <div>
        <van-image
                width="100%"
                height="100%"
                fit="contain"
                :src="img"
        />
    </div>

</template>

<script>
    import img from '@/assets/imgs/购物车.jpg'
    export default {
        name: "Cart",
        data(){
            return{
                img:img,
            }
        }
    }
</script>

<style scoped>

</style>