package com.algaworks.algafood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

//DataSize maxSize recebe uma string que possui o valor aceitavel do arquivo
//DataSize.parse(constraintAnnotation.max()) converte em String em DataSize

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private DataSize maxSize;
	
	@Override
	public void initialize(FileSize constraintAnnotation) {
		this.maxSize = DataSize.parse(constraintAnnotation.max());
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value == null || value.getSize() <= this.maxSize.toBytes();
	}

}