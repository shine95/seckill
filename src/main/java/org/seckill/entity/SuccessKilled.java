package org.seckill.entity;

import java.util.Date;

/**
 * 秒杀成功表
 */
public class SuccessKilled {
    private long seckillId;
    private long userPhone;
    private short state;
    private Date ceateTime;

    //多对一
    private Seckill seckill;

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", ceateTime=" + ceateTime +
                '}';
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCeateTime() {
        return ceateTime;
    }

    public void setCeateTime(Date ceateTime) {
        this.ceateTime = ceateTime;
    }
}
