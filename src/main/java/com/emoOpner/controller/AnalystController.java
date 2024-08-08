package com.emoOpner.controller;

import com.emoOpner.response.AResponse;
import com.emoOpner.service.WordAnalystService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.emoOpner.constant.SwaggerDocumentConstant.OPERATE;

@RestController
@Api(tags = "分析接口")
@RequestMapping(value = "/a/analyze")
public class AnalystController {
    @Autowired
    WordAnalystService wordAnalystService;

    @ApiOperation(notes = "doTokenization", value = OPERATE + "微博正文分词")
    @RequestMapping(value = "/doTokenization", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AResponse doTokenization(@RequestBody Map<String,Object> map) {
        return wordAnalystService.doTokenization();
    }
}
