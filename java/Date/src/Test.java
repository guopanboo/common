import java.util.Date;

public class Test {
	public static void main(String[] args) {
		//System.out.println(DateUtil.getStrByDate(DateUtil.getDaysAgoBegin(50), true));
		System.out.println(DateUtil.getStrByDate(new Date(), "HH:ss"));
	}
}
