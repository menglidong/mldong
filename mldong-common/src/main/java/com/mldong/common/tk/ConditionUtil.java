package com.mldong.common.tk;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.mldong.common.base.OperateTypeEnum;
import com.mldong.common.base.WhereParam;

/**
 * tk条件工具封装
 * @author mldong
 *
 */
public class ConditionUtil {
	private ConditionUtil() {}
	/**
	 * 通过查询构型构造tk查询条件
	 * @param clazz
	 * @param list
	 * @return
	 */
	public static Condition buildCondition(Class<?> clazz,List<WhereParam> list) {
		Condition condition = new Condition(clazz);
		Criteria criteria = condition.createCriteria();
		for(WhereParam model: list) {
			switch (model.getOperateType()) {
			case EQ:
				criteria.andEqualTo(model.getPropertyName(), model.getPropertyValue());
				break;
			case NE:
				criteria.andNotEqualTo(model.getPropertyName(), model.getPropertyValue());
				break;
			case GT:
				criteria.andGreaterThan(model.getPropertyName(), model.getPropertyValue());
				break;
			case GE:
				criteria.andGreaterThanOrEqualTo(model.getPropertyName(), model.getPropertyValue());
				break;
			case LT:
				criteria.andLessThan(model.getPropertyName(), model.getPropertyValue());
				break;
			case LE:
				criteria.andLessThanOrEqualTo(model.getPropertyName(), model.getPropertyValue());
				break;
			case BT:
				List<Object> listObject = (List<Object>) model.getPropertyValue();
				criteria.andBetween(model.getPropertyName(), listObject.get(0),listObject.get(1));
				break;
			case NBT:
				listObject = (List<Object>) model.getPropertyValue();
				criteria.andNotBetween(model.getPropertyName(), listObject.get(0),listObject.get(1));
				break;
			case LIKE:
				criteria.andLike(model.getPropertyName(), "%"+model.getPropertyValue()+"%");
				break;
			case LLIKE:
				criteria.andLike(model.getPropertyName(), "%"+model.getPropertyValue());
				break;
			case RLIKE:
				criteria.andLike(model.getPropertyName(), model.getPropertyValue()+"%");
				break;
			case IN:
				listObject = (List<Object>) model.getPropertyValue();
				criteria.andIn(model.getPropertyName(), listObject );
				break;
			case NIN:
				listObject = (List<Object>) model.getPropertyValue();
				criteria.andNotIn(model.getPropertyName(), listObject );
			case OR:
				Map<String,Object> map = (Map<String, Object>) model.getPropertyValue();
				WhereParam param = new WhereParam();
				param.setOperateType(OperateTypeEnum.valueOf(map.get("operateType").toString()));
				param.setPropertyName(map.get("propertyName").toString());
				param.setPropertyValue(map.get("propertyValue"));
				switch (param.getOperateType()) {
				case EQ:
					criteria.orEqualTo(param.getPropertyName(), param.getPropertyValue());
					break;
				case NE:
					criteria.orNotEqualTo(param.getPropertyName(), param.getPropertyValue());
					break;
				case GT:
					criteria.orGreaterThan(param.getPropertyName(), param.getPropertyValue());
					break;
				case GE:
					criteria.orGreaterThanOrEqualTo(param.getPropertyName(), param.getPropertyValue());
					break;
				case LT:
					criteria.orLessThan(param.getPropertyName(), param.getPropertyValue());
					break;
				case LE:
					criteria.orLessThanOrEqualTo(param.getPropertyName(), param.getPropertyValue());
					break;
				case BT:
					listObject = (List<Object>) param.getPropertyValue();
					criteria.andBetween(param.getPropertyName(), listObject.get(0),listObject.get(1));
					break;
				case NBT:
					listObject = (List<Object>) param.getPropertyValue();
					criteria.orNotBetween(param.getPropertyName(), listObject.get(0),listObject.get(1));
					break;
				case LIKE:
					criteria.orLike(param.getPropertyName(), "%"+param.getPropertyValue()+"%");
					break;
				case LLIKE:
					criteria.orLike(param.getPropertyName(), "%"+param.getPropertyValue());
					break;
				case RLIKE:
					criteria.orLike(model.getPropertyName(), param.getPropertyValue()+"%");
					break;
				case IN:
					listObject = (List<Object>) param.getPropertyValue();
					criteria.orIn(param.getPropertyName(), listObject );
					break;
				case NIN:
					listObject = (List<Object>) param.getPropertyValue();
					criteria.orNotIn(param.getPropertyName(), listObject );
				default:
					break;
				}
			default:
				break;
			}
		}
		return condition;
	}
}
