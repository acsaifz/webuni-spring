package hu.webuni.hr.acsaifz.mapper;

import hu.webuni.hr.acsaifz.dto.CompanyDto;
import hu.webuni.hr.acsaifz.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses={EmployeeMapper.class})
public interface CompanyMapper {
    CompanyDto companyToDto(Company company);

    List<CompanyDto> companiesToDto(List<Company> companies);

    Company dtoToCompany(CompanyDto companyDto);
}
