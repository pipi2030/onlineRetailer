<template>
    <van-image
            width="100%"
            height="100%"
            fit="contain"
            :src="img"
    />
</template>

<script>
    import img from '@/assets/imgs/个人中心.jpg'
    export default {
        name: "Center",
        data(){
            return{
                img:img,
            }
        }
    }
</script>

<style scoped>

</style>