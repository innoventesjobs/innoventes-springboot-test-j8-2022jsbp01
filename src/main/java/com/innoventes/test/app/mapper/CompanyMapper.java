package com.innoventes.test.app.mapper;

import java.util.List;
import java.util.Set;

import com.innoventes.test.app.dto.CompanyDTO;
import com.innoventes.test.app.entity.Company;

public interface CompanyMapper {

	CompanyDTO getCompanyDTO(Company entity);

	List<CompanyDTO> getCompanyDTOList(List<Company> entityList);

	Set<CompanyDTO> getCompanyDTOSet(Set<Company> entitySet);

	Company getCompany(CompanyDTO dto);
}
