package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "granted_permissions")
public class GrantedPermissionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private OperationEntity  operationEntity;

    public GrantedPermissionEntity() {
    }

    public GrantedPermissionEntity(Long id, RoleEntity roleEntity, OperationEntity operationEntity) {
        this.id = id;
        this.roleEntity = roleEntity;
        this.operationEntity = operationEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public OperationEntity getOperationEntity() {
        return operationEntity;
    }

    public void setOperationEntity(OperationEntity operationEntity) {
        this.operationEntity = operationEntity;
    }
}
