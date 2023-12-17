package com.sky.controller.admin;


import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("aminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺状态管理端接口")
@Slf4j
public class ShopController {
    private static final String key = "shop_status";

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation("营业状态设置")
    public Result SetStatus(@PathVariable Integer status){
        log.info("店铺营业状态为:{}", status == 1 ? "营业中" : "非营业");
        redisTemplate.opsForValue().set(key, status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("营业状态查询")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(key);
        return Result.success(status);
    }
}
