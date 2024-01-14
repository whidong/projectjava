package Main;

import java.awt.Button;


public class login implements idboard{ //주석 3 : 인터페이스 구현 클레스 선언
	private String id;
	private int pd;
	private Button btnId;

	@Override
	public boolean check(String eid, String epd) { //주석 5 : 다형성 인터페이스
		if(eid.equals(idboard.id) && epd.equals(idboard.pd)) {
			return true;
		}else {
			return false;
		}
	}

	}


		


