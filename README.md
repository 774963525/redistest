# redistest

使用redis写了一个高并发的抢购系统

思路:
### 一.先查缓存有无商品信息,无则查数据库
### 二.用户点击,将userId,productId,content发给后台
### 三.后台接收,拼接用户id和商品id,使用redis set塞入缓存,
  (1)因为set唯一特性,如果成功证明用户有抢购资格,进入四.
  (2)失败,则证明该用户已经参与抢购,取消资格.
### 四. 判断商品库存的数量,
  (1)如果为0,则return
  (2)操作缓存中的库存`-1`
      [1] 返回结果>=0 则生成订单  
      [2] 小于0 售罄 ,返还参与资格
### 五. main中使用
      ```
      @Scheduled(fixedDelay=2000)
          public void test() throws InterruptedException {
              insert.insert();
          }
      ```
    两秒查一次缓存,有数据则开始循环拿取第一个值插入数据库.

## 总结:
    mysql不能实现高并发,为了解决这一问题,我们使用redis.
    api全程操作redis而不连接数据库,解决高并发问题.
