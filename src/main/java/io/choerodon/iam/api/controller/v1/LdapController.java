package io.choerodon.iam.api.controller.v1;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.iam.api.dto.LdapDTO;
import io.choerodon.iam.api.dto.UserDTO;
import io.choerodon.iam.app.service.LdapService;
import io.choerodon.swagger.annotation.Permission;

/**
 * @author wuguokai
 */
@RestController
@RequestMapping("/v1/organizations/{organization_id}/ldaps")
public class LdapController {

    private LdapService ldapService;

    public LdapController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    /**
     * 添加Ldap
     *
     * @param organizationId
     * @param ldapDTO
     * @return ldapDTO
     */
    @Permission(level = ResourceLevel.ORGANIZATION, roles = {"organizationAdmin"})
    @ApiOperation(value = "添加Ldap")
    @PostMapping
    public ResponseEntity<LdapDTO> create(@PathVariable("organization_id") Long organizationId,
                                          @RequestBody LdapDTO ldapDTO) {
        return new ResponseEntity<>(ldapService.create(organizationId, ldapDTO), HttpStatus.OK);
    }

    /**
     * 更新Ldap
     *
     * @param organizationId
     * @param id
     * @param ldapDTO
     * @return ldapDTO
     */
    @Permission(level = ResourceLevel.ORGANIZATION, roles = {"organizationAdmin"})
    @ApiOperation(value = "更新Ldap")
    @PostMapping(value = "/{id}")
    public ResponseEntity<LdapDTO> update(@PathVariable("organization_id") Long organizationId,
                                          @PathVariable("id") Long id, @RequestBody LdapDTO ldapDTO) {
        return new ResponseEntity<>(ldapService.update(organizationId, id, ldapDTO), HttpStatus.OK);
    }

    /**
     * 根据组织id查询Ldap
     *
     * @param organizationId
     * @return ldapDTO
     */
    @Permission(level = ResourceLevel.ORGANIZATION, roles = {"organizationAdmin"})
    @ApiOperation(value = "根据组织id查询Ldap")
    @GetMapping
    public ResponseEntity<LdapDTO> queryByOrgId(@PathVariable("organization_id") Long organizationId) {
        return new ResponseEntity<>(ldapService.queryByOrgId(organizationId), HttpStatus.OK);
    }

    /**
     * 根据组织id删除Ldap
     *
     * @param organizationId
     * @return ldapDTO
     */
    @Permission(level = ResourceLevel.ORGANIZATION, roles = {"organizationAdmin"})
    @ApiOperation(value = "根据组织id删除Ldap")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> query(@PathVariable("organization_id") Long organizationId,
                                         @PathVariable("id") Long id) {
        return new ResponseEntity<>(ldapService.delete(organizationId, id), HttpStatus.OK);
    }

    /**
     * 测试ldap连接
     *
     * @return 是否连接成功
     */
    @Permission(level = ResourceLevel.ORGANIZATION, roles = {"organizationAdmin"})
    @ApiOperation(value = "测试ldap连接")
    @GetMapping("/test_connect")
    public ResponseEntity<Boolean> testConnect(@PathVariable("organization_id") Long organizationId) {
        return new ResponseEntity<>(ldapService.testConnect(organizationId), HttpStatus.OK);
    }

    /**
     * 同步ldap用户
     */
    @Permission(level = ResourceLevel.ORGANIZATION, roles = {"organizationAdmin"})
    @ApiOperation(value = "同步ldap用户")
    @PostMapping("/sync_users")
    public ResponseEntity syncUsers(@PathVariable("organization_id") Long organizationId, @RequestBody UserDTO userDTO) {
        ldapService.syncLdapUser(organizationId, userDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}