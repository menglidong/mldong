package ${package.Entity?replace('entity','vo')};

import ${package.Entity}.${entity};
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@ApiModel(value = "${entity}VO对象", description = "${table.comment!}VO")
public class ${entity}VO extends ${entity} {
}
