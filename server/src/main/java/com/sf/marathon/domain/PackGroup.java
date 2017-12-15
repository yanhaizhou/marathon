package com.sf.marathon.domain;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tt_pack_group")
public class PackGroup {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "Pid")
    private String pid;
    @Column(name = "begin_time")
    private Date beginTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "group_num")
    private Integer groupNum;
    @Column(name = "finish")
    private Byte finish;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "finish_time")
    private Date finishTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "pid")//这里设置JoinColum设置了外键的名字，并且orderItem是关系维护端
    private ProMarketBase customer;

    @Version
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Byte getFinish() {
        return finish;
    }

    public void setFinish(Byte finish) {
        this.finish = finish;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
