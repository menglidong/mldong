package ${package.Other};

import ${g.basePackage}.base.PageParam;
import ${package.Entity}.${entity};
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
// ==
public class ${entity}PageParam extends PageParam<${entity}> {
}