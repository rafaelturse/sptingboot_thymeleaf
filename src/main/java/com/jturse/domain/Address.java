package com.jturse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jturse.domain.enums.UF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_adresses")
public class Address extends AbstractEntity<Long> {

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(name = "public_place", nullable = false)
	private String publicPlace;

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(name = "neighborhood", nullable = false)
	private String neighborhood;

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(name = "city", nullable = false)
	private String city;

	@NotNull(message = "{NotNull.address.uf}")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;

	@NotBlank
	@Size(min = 9, max = 9, message = "{Size.address.cep}")
	@Column(nullable = false, length = 9)
	private String cep;

	@NotNull(message = "{NotNull.address.number}")
	@Digits(integer = 5, fraction = 0)
	@Column(nullable = false, length = 5)
	private Integer number;

	@Size(max = 255)
	private String complement;
}