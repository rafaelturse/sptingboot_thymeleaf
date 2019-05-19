package com.jturse.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jturse.domain.Department;
import com.jturse.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartamentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/create")
	public String create(Department departament) {
		return "department/create";
	}

	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("departments", departmentService.listAll());
		return "department/list";
	}

	@PostMapping("/save")
	public String save(@Valid Department departament, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "department/create";
		}

		departmentService.save(departament);

		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");

		return "redirect:/department/create";
	}

	@GetMapping("/update/{id}")
	public String preUpdate(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("department", departmentService.findById(id));
		return "department/create";
	}

	@PostMapping("/update")
	public String update(@Valid Department department, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "department/create";
		}

		departmentService.update(department);
		attr.addFlashAttribute("success", "Departamento editado com sucesso.");
		return "redirect:/department/create";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, ModelMap model) {
		if (departmentService.departmentHasPosition(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		} else {
			departmentService.delete(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}

		return list(model);
	}
}