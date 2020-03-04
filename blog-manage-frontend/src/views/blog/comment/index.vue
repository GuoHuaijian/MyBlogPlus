<template>
  <div>
    <iframe src="http://changyan.kuaizhan.com/forum/list" id="changyan" ></iframe>
  </div>
</template>

<script>
  export default {
    name: 'Comment',
    data () {
      return {
      }
    },
    mounted(){
      /**
       * iframe-宽高自适应显示
       */
      function changeMobsfIframe(){
        const changyan = document.getElementById('changyan');
        const deviceWidth = document.body.clientWidth;
        const deviceHeight = document.body.clientHeight;
        changyan.style.width = (Number(deviceWidth)-120) + 'px'; //数字是页面布局宽度差值
        changyan.style.height = (Number(deviceHeight)-80) + 'px'; //数字是页面布局高度差
      }

      changeMobsfIframe()

      window.onresize = function(){
        changeMobsfIframe()
      }
    },
  }
</script>

<style scoped>

</style>
