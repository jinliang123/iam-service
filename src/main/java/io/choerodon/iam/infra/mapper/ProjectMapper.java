package io.choerodon.iam.infra.mapper;

import io.choerodon.iam.infra.dataobject.ProjectDO;
import io.choerodon.mybatis.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuguokai
 */
public interface ProjectMapper extends BaseMapper<ProjectDO> {

    List<ProjectDO> fulltextSearch(@Param("project") ProjectDO projectDO,
                                   @Param("param") String param);

    List selectProjectsFromMemberRoleByOption(@Param("userId") Long userId,
                                              @Param("projectDO") ProjectDO projectDO);
}
