package cn.xjtu.track.service;

import cn.xjtu.track.common.Result;
import cn.xjtu.track.entity.DataDetail;

import java.util.List;

public interface DataDetailService {
	/** 初始化DataDetail数据*/
	public DataDetail initDataDetail(String data);
	
	public Result tranDetail(Long dataItemId);

	public List<DataDetail> getDataDetailList(Long itemId);

	public List<DataDetail> correctDataDetailList(List<DataDetail> details);

	public List<DataDetail> correctDataDetailList2(List<DataDetail> details);
}
