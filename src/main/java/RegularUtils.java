import org.junit.jupiter.api.Test;

//正则校验
public class RegularUtils {
    //手机号校验
    public static final String phoneUtil = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
    //邮箱校验
    public static final String emailUtil = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    //邮政编码校验
    public static final String postalCodeUtil = "[1-9]\\d{5}(?!\\d)";
    //日期校验： 年-月-日
    public static final String dateUtil = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    @Test
    public void test(){
        String phone = "15658109024";

        String email = "743279095@qq.com";

        String postalCode = "310000";

        String date ="2023-10-21";

        if(!phone .matches(phoneUtil)){
            System.out.println("phone验证不通过");
        }
        if(!email .matches(emailUtil)){
            System.out.println("email验证不通过");
        }
        if(!postalCode.matches(postalCodeUtil)){
            System.out.println("postalCode验证不通过");
        }
        if(!date.matches(dateUtil)){
            System.out.println("date验证不通过");
        }
    }
}
