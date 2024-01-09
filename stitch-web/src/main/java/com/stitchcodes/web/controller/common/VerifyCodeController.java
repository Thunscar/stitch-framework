package com.stitchcodes.web.controller.common;

import com.google.code.kaptcha.Producer;
import com.stitchcodes.common.api.AjaxResult;
import com.stitchcodes.common.config.StitchConfig;
import com.stitchcodes.common.constant.CacheConstants;
import com.stitchcodes.common.controller.BaseController;
import com.stitchcodes.common.redis.RedisCache;
import com.stitchcodes.common.utils.IdUtils;
import com.stitchcodes.core.service.SysConfigService;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @Author: stitch
 * @Date: 2023/4/27 20:37
 * @Description:
 */
@RestController
public class VerifyCodeController extends BaseController {

    private static final String CHAR_VERIFY_TYPE = "char";
    private static final String MATH_VERIFY_TYPE = "math";
    private static final String MATH_DIVIDER = "@";

    @Resource
    private SysConfigService configService;
    @Resource
    private StitchConfig stitchConfig;
    @Resource
    private RedisCache redisCache;
    @Resource(name = "verifyCodeProducer")
    private Producer verifyCodeProducer;
    @Resource(name = "verifyMathProducer")
    private Producer verifyMathProducer;


    @GetMapping("/verifyImage")
    public AjaxResult getVerifyImage() throws IOException {
        //获取验证码开关
        AjaxResult result = new AjaxResult();
        boolean verifyEnable = configService.selectVerifyEnabled();
        result.put("verifyEnable", verifyEnable);
        if (!verifyEnable) {
            return result;
        }

        //获取验证码类型
        String verifyCodeType = stitchConfig.getVerifyCodeType();

        //创建验证码文本和图片
        String imageStr = null;
        String verifyStr = null;
        BufferedImage verifyImage = null;
        if (CHAR_VERIFY_TYPE.equals(verifyCodeType)) {
            imageStr = verifyStr = verifyCodeProducer.createText();
            verifyImage = verifyCodeProducer.createImage(imageStr);
        } else if (MATH_VERIFY_TYPE.equals(verifyCodeType)) {
            String math = verifyMathProducer.createText();
            imageStr = math.substring(0, math.indexOf(MATH_DIVIDER));
            verifyStr = math.substring(math.indexOf(MATH_DIVIDER) - 1, math.length());
            verifyImage = verifyMathProducer.createImage(imageStr);
        }

        //生成uuid将验证码验证code缓存
        String uuid = IdUtils.generateUUID();
        String verifyCodeKey = CacheConstants.VERIFY_CODE_KEY + uuid;
        redisCache.setCacheObject(verifyCodeKey, verifyStr, 60, TimeUnit.SECONDS);

        //将图片写入输入流
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(verifyImage, "jpg", os);

        //将uuid与验证码图片返回
        result.put("uuid", uuid);
        result.put("verifyImage", Base64.getEncoder().encodeToString(os.toByteArray()));
        return result;
    }
}
