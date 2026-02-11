package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("AdminShopController")
@RequestMapping("/admin/shop")
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

    /**
     * 设置营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public Result setStatus(@PathVariable("status") Integer status) {
        log.info("设置营业状态：{}", status);
        shopService.setStatus(status);
        return Result.success();
    }
}
