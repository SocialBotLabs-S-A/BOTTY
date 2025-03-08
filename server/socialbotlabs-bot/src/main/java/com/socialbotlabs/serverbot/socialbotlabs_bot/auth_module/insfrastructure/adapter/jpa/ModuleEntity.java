package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.jpa;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "modules")
public class ModuleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moduleName;

    private String basePath;

    public ModuleEntity() {
    }

    public ModuleEntity(Long id, String moduleName, String basePath) {
        this.id = id;
        this.moduleName = moduleName;
        this.basePath = basePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
