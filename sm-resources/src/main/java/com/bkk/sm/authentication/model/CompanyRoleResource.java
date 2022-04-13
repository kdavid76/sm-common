package com.bkk.sm.authentication.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CompanyRoleResource implements Serializable {
    private String companyCode;
    private String companyName;
    private String role;
}
