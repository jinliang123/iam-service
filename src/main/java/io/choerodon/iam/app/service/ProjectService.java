package io.choerodon.iam.app.service;

import io.choerodon.core.domain.Page;
import io.choerodon.iam.api.dto.ProjectDTO;
import io.choerodon.iam.api.dto.UserDTO;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * @author flyleft
 * @date 2018/3/26
 */
public interface ProjectService {

    ProjectDTO queryProjectById(Long projectId);

    Page<UserDTO> pagingQueryTheUsersOfProject(Long id, Long userId, PageRequest pageRequest, String param);

    ProjectDTO update(ProjectDTO projectDTO);

    ProjectDTO disableProject(Long id);
}
