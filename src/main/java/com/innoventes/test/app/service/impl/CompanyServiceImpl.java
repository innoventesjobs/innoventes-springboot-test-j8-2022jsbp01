package com.innoventes.test.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import antlr.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innoventes.test.app.entity.Company;
import com.innoventes.test.app.error.ApplicationErrorCodes;
import com.innoventes.test.app.exception.ResourceNotFoundException;
import com.innoventes.test.app.exception.ValidationException;
import com.innoventes.test.app.repository.CompanyRepository;
import com.innoventes.test.app.service.CompanyService;
import com.innoventes.test.app.util.ServiceHelper;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ServiceHelper serviceHelper;

	@Override
	public List<Company> getAllCompanies() {
		ArrayList<Company> companyList = new ArrayList<Company>();
		companyRepository.findAll().forEach(companyList::add);
		return companyList;
	}

	@Override
	public Company addCompany(Company company) throws ValidationException {
		if (company.getCompanyName().length() >= 5 && company.getEmail().contains("@")) {
			if (company.getStrength() == null || company.getStrength() > 0) {
				if (companyCodeValidator(company)) {
					return companyRepository.save(company);
				}
			}
		}
		return  company;
	}

	private boolean companyCodeValidator(Company company) {
        String companyCode = company.getCompanyCode();
        if (companyCode != null) {
            if (companyCode.length() == 5) {
                if (companyCode.charAt(4) == 'N' || companyCode.charAt(4) == 'E') {
                    if (companyCode.charAt(0) >= 'A' && companyCode.charAt(0) <= 'Z'
                            && companyCode.charAt(1) >= 'A' && companyCode.charAt(1) <= 'Z') {
                        return Character.isDigit(companyCode.charAt(2)) && Character.isDigit(companyCode.charAt(3));
                    }
                }
            }
        } else {
            return true;
        }
        return false;
	}

	@Override
	public Company updateCompany(Long id, Company company) throws ValidationException {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		company.setId(existingCompanyRecord.getId());
		return companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long id) {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		companyRepository.deleteById(existingCompanyRecord.getId());
	}

	@Override
	public Company getCompanyById(Long id) {
		return  companyRepository.getReferenceById(id);
	}

	@Override
	public Company getCompanyByCompanyCode(String companyCode) {
		return companyRepository.findCompanyByCompanyCode(companyCode);
	}
}
