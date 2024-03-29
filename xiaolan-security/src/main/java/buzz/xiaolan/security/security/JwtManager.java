package buzz.xiaolan.security.security;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/28
 * @Description JwtManager
 */
@Slf4j
public class JwtManager {

    private static final String KEY = "QUkNCf9zYAlqGsuet4oa80cTyVEPmwSR";
    private static final String REFRESH_KEY = "wEn2L5STG7bWtohu0ApXKlz6sQBjxrMC";
    public static final Integer EXPIRES = 2;
    public static final Integer REFRESH_EXPIRES = 3;

    /**
     * 生成Token，为了让Knows也能成功校验，这里使用的Key必须和Knows保持一致
     *
     * @param payloads 有效载荷
     * @return java.lang.String
     * @author Wang Chenguang
     */
    public static String generatedToken(Map<String, ?> payloads) {
        DateTime dateTime = DateUtil.date();
        return JWT.create()
                .setKey(KEY.getBytes(StandardCharsets.UTF_8))
                .setJWTId(IdUtil.simpleUUID())
                .addPayloads(payloads)
                .setIssuer("CaoGuo")
                .setIssuedAt(dateTime)
                .setSubject("Web")
                .setExpiresAt(DateUtil.offsetHour(dateTime, EXPIRES))
                .sign();
    }

    public static String generatedToken(Map<String, ?> payloads, Date expiresAt) {
        return JWT.create()
                .setKey(KEY.getBytes(StandardCharsets.UTF_8))
                .setJWTId(IdUtil.simpleUUID())
                .addPayloads(payloads)
                .setIssuer("CaoGuo")
                .setIssuedAt(DateUtil.date())
                .setSubject("Web")
                .setExpiresAt(expiresAt)
                .sign();
    }


    /**
     * 生成Token，为了让Knows也能成功校验，这里使用的Key必须和Knows保持一致
     *
     * @param payloads 有效载荷
     * @return java.lang.String
     * @author Wang Chenguang
     */
    public static String generatedRefreshToken(Map<String, ?> payloads) {
        DateTime dateTime = DateUtil.date();
        return JWT.create()
                .setKey(REFRESH_KEY.getBytes(StandardCharsets.UTF_8))
                .setJWTId(IdUtil.simpleUUID())
                .addPayloads(payloads)
                .setIssuer("CaoGuo")
                .setIssuedAt(dateTime)
                .setSubject("Web")
                .setExpiresAt(DateUtil.offsetDay(dateTime, REFRESH_EXPIRES))
                .sign();
    }

    public static String generatedRefreshToken(Map<String, ?> payloads, Date expiresAt) {
        return JWT.create()
                .setKey(REFRESH_KEY.getBytes(StandardCharsets.UTF_8))
                .setJWTId(IdUtil.simpleUUID())
                .addPayloads(payloads)
                .setIssuer("CaoGuo")
                .setIssuedAt(DateUtil.date())
                .setSubject("Web")
                .setExpiresAt(expiresAt)
                .sign();
    }

    /**
     * 验证JWT Token有效性
     *
     * @param token Token
     * @return java.lang.Boolean
     * @author Wang Chenguang
     */
    public static Boolean verify(String token) {
        try {
            return JWTUtil.verify(token, KEY.getBytes(StandardCharsets.UTF_8));
        }catch (Exception ex){
            log.warn(ex.getMessage());
            return false;
        }
    }

    /**
     * 验证JWT Token有效性
     *
     * @param token Token
     * @return java.lang.Boolean
     * @author Wang Chenguang
     */
    public static Boolean verifyRefresh(String token) {
       try {
           return JWTUtil.verify(token, REFRESH_KEY.getBytes(StandardCharsets.UTF_8));
       }catch (Exception ex){
           log.warn(ex.getMessage());
           return false;
       }
    }


    /**
     * 检查JWT的以下三两个时间：
     *
     * <ul>
     *     <li>{@link JWTPayload#NOT_BEFORE}：被检查时间必须晚于生效时间</li>
     *     <li>{@link JWTPayload#EXPIRES_AT}：被检查时间必须早于失效时间</li>
     *     <li>{@link JWTPayload#ISSUED_AT}：签发时间必须早于失效时间</li>
     * </ul>
     * <p>
     *
     * @param token Token
     * @return java.lang.Boolean
     * @author Wang Chenguang
     */
    public static Boolean validateDate(String token) {
        try {
            JWTValidator.of(token).validateDate();
            return true;
        } catch (Exception ex) {
            log.error("Token has expired: {}", ex.getMessage());
            return false;
        }
    }

    /**
     * 解析JWT Token
     *
     * @param token Token
     * @return cn.hutool.json.JSONObject
     * @author Wang Chenguang
     */
    public static JSONObject parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.getPayloads();
    }
}
