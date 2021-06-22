import java.io.UnsupportedEncodingException;

public class PwdTest {
    public static void main(final String[] args) throws UnsupportedEncodingException {
        System.out.println(com.codebaobao.utils.PwdUtils.encryption("123456", "admin"));
    }

}
