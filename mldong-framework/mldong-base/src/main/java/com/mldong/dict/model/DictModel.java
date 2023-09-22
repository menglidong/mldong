package com.mldong.dict.model;

import cn.hutool.core.collection.CollectionUtil;
import com.mldong.base.LabelValueVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DictModel implements Serializable{

	private static final long serialVersionUID = -832930180178912158L;

	@ApiModelProperty("字典名称")
	private String name;

	@ApiModelProperty("字典唯一编码")
	private String dictKey;
	/**
	 * 字典项
	 */
	@ApiModelProperty("字典项集合")
	private List<DictItemModel> items;

	/**
	 * 将label value转成dictModel
	 * @param list
	 * @return
	 */
	public static DictModel toDictModel(List<LabelValueVO> list) {
		if(CollectionUtil.isEmpty(list)) return null;
		DictModel dictModel = new DictModel();
		dictModel.setName("默认转换");
		dictModel.setDictKey("default");
		dictModel.setItems(list.stream().map(item->{
			DictItemModel vo = new DictItemModel();
			vo.setName(item.getLabel());
			vo.setDictItemValue(item.getValue());
			return vo;
		}).collect(Collectors.toList()));
		return dictModel;
	}
}
