package com.qg.controller;

import com.qg.dto.ReturnResult;
import com.qg.dto.ReturnResultUtils;
import com.qg.service.LocalGoodsService;
import com.qg.vo.GoodsVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v")
public class QgGoodsController {
    @Resource
    private LocalGoodsService localGoodsService;

    @GetMapping("/queryGoodsById")
    public ReturnResult queryGoodsById(String id) throws Exception {
        GoodsVo vo = localGoodsService.getGoodsById(id);
        return ReturnResultUtils.returnSuccess(vo);
    }

    @PostMapping("/getGoods")
    public ReturnResult getGoods(@RequestHeader String token, @RequestParam String goodsId) throws Exception {
        String userId = token.split("-")[2];
        return localGoodsService.getGoods(userId, goodsId);
    }
}
