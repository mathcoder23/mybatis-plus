package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>  {

}
</#if>
