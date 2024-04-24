package pres.xgo.fim.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 utils
 */
@Slf4j
public class RegexUtils {
    private RegexUtils() {
    }

    /**
     * 获取字符串第一串数字
     * @param input [37.2100元/公斤 395个门店][25.0622元/公斤 69个门店][25.8392元/公斤 2个门店][27.5684元/公斤 1个门店]
     * @return 37.2100
     */
    public static String extractFirstNumber(String input) {
        // 正则表达式匹配第一个数字
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);
        // 如果找到匹配的数字，则返回
        if (matcher.find()) {
            return matcher.group();
        } else {
            // 没有找到匹配的数字
            return "";
        }
    }

    /**
     * 获取字符串第一串数字
     * @param input
     * @return 返回 BigDecimal
     */
    public static BigDecimal extractFirstNumberBd(String input){
        String numStr = extractFirstNumber(input);
        if(StringUtils.isEmpty(numStr)){
            return BigDecimal.ZERO;
        }else {
            return new BigDecimal(numStr);
        }
    }

    public static void main(String[] args) {
        String input = "[37.2100元/公斤 395个门店][25.0622元/公斤 69个门店][25.8392元/公斤 2个门店][27.5684元/公斤 1个门店]";
        log.info("extractFirstNumber : {}",extractFirstNumber(input));
    }
}
