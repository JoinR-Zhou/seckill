package org.seckill.dto;

import org.seckill.entity.SuccessSeckill;
import org.seckill.enums.SeckillStatEnum;

/**
 * 封装秒杀执行后的结果
 * @author Administrator
 *
 */
public class SeckillExecution {

	private long seckillId;
	//执行秒杀结果的状态
	private int state;
	//状态的标示
	private String stateInfo;
	//成功秒杀对象
	private SuccessSeckill successSeckill;
	//秒杀失败
	public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
		super();
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStatInfo();
	}
	//成功秒杀
	public SeckillExecution(long seckillId, SeckillStatEnum statEnum,SuccessSeckill successSeckill) {
		this(seckillId,statEnum);
		this.successSeckill = successSeckill;
	}
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessSeckill getSuccessSeckill() {
		return successSeckill;
	}
	public void setSuccessSeckill(SuccessSeckill successSeckill) {
		this.successSeckill = successSeckill;
	}
	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", successSeckill="
				+ successSeckill + "]";
	}
	
}
