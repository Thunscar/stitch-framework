package com.stitchcodes.web.controller.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: stitch
 * @Date: 2023/4/28 15:15
 * @Description:
 */
@RestController
@RequestMapping("/")
public class CommonController {

    @GetMapping()
    public String rootRequest() {
        return "Welcome to use Stitch Application...";
    }


}
