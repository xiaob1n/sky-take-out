package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("UserShopController")
@RequestMapping("/user/shop")
@Slf4j
@Api(tags = "店铺操作接口")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 查询营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("查询营业状态")
    public Result<Integer> getStatus() {
        log.info("查询营业状态");
        Integer status = shopService.getStatus();
        return Result.success(status);
    }
}
