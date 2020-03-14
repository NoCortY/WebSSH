package cn.objectspace.webssh.util;

//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


public class StringUtil {

    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

    public static boolean isEmpty(String str) {
        str = String.valueOf(str);
        if (str.equals("null") || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String... strs) {
        if (strs.length == 0) {
            return true;
        }
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 转换成形如
     * 'AAA', 'BBB', 'CCC'的形式
     *
     * @param list
     * @return
     */
    public static String toSqlStringArray(List<Object> list) {
        StringBuffer result = new StringBuffer();
        int size = list.size();
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < size; i++) {
                result.append((i == 0) ? "" : ", ");
                result.append("'" + (String) list.get(i) + "'");
            }
        }
        return result.toString();
    }


    /**
     * 32位 UUID 字符串
     *
     * @return
     */
    public static String uuid32() {
        return uuid64().replace("-", "");
    }

    /**
     * 64 位 UUID 字符串
     *
     * @return
     */
    public static String uuid64() {
        return UUID.randomUUID().toString();
    }

    /**
     * 字符串拼接工具
     *
     * @param charSequences
     * @return
     */
    public synchronized static String appendStr(@NotNull CharSequence... charSequences) {
        StringBuilder builder = new StringBuilder();
        for (CharSequence charSequence : charSequences) {
            builder.append(charSequence);
        }
        return builder.toString();
    }

//    /**
//     * 获取字符串拼音
//     *
//     * @param src
//     * @return
//     */
//    public synchronized static String getPinYin(@NotNull String src) throws BadHanyuPinyinOutputFormatCombination {
//        String pinyinString = "";
//        try {
//            HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
//            outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//            pinyinString = PinyinHelper.toHanYuPinyinString(src, outputFormat, "", false);
//        } catch (BadHanyuPinyinOutputFormatCombination e) {
//            log.error(e.getMessage(), e);
//            throw e;
//        }
//        return pinyinString;
//    }

}
