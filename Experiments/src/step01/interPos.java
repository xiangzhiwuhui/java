package step01;

import java.util.List;

public class interPos {
	public static int commomPos(List pos01,List pos02){
		pos01.retainAll(pos02);
		return pos01.size();
	}

}
