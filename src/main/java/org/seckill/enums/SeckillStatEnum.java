package org.seckill.enums;

public enum SeckillStatEnum {

	SUCCESS(1,"秒杀成功"),
	END(0,"秒杀关闭"),
	REPEAT_KILL(-1,"重复秒杀"),
	INNER_ERROR(-2,"系统错误"),
	DATE_REWRITE(-3,"数据篡改");
	
	private int state;
	private String  statInfo;
	
	public static SeckillStatEnum stateOf(int index){
		for (SeckillStatEnum state : values()) {
			if(state.getState()==index){
				return state;
			}
		}
		return null;
	}
	private SeckillStatEnum(int state, String statInfo) {
		this.state = state;
		this.statInfo = statInfo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStatInfo() {
		return statInfo;
	}
	public void setStatInfo(String statInfo) {
		this.statInfo = statInfo;
	}
	
	
}
