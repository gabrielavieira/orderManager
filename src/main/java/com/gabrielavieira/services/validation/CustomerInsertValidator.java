package com.gabrielavieira.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gabrielavieira.domain.enums.CustomerType;
import com.gabrielavieira.dto.CustomerNewDTO;
import com.gabrielavieira.resources.exception.FieldMessage;
import com.gabrielavieira.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {
	@Override
	public void initialize(CustomerInsert ann) {
	}

	@Override
	public boolean isValid(CustomerNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getCustomerType().equals(CustomerType.FISICAL_PERSON.getCode()) &&
				!BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "A valid CPF is necessary"));
		}else if(objDto.getCustomerType().equals(CustomerType.LEGAL_PERSON.getCode()) &&
				!BR.isValidCNPJ(objDto.getCpfOrCnpj())){
			list.add(new FieldMessage("cpfOrCnpj", "A valid CNPJ is necessary"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}