package com.jturse.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jturse.domain.Department;
import com.jturse.domain.Position;
import com.jturse.service.IDepartmentService;
import com.jturse.service.IPositionService;

@Controller
@RequestMapping("/position")
public class PositionController {

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IPositionService positionService;

	@GetMapping("/create")
	public String create(Position position) {
		return "position/create";
	}

	@GetMapping("/list")
	public String listar(ModelMap model) {
		model.addAttribute("positions", positionService.listAll());
		return "position/list";
	}

	@PostMapping("/save")
	public String salvar(@Valid Position position, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "position/create";
		}

		positionService.save(position);

		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");

		return "redirect:/position/create";
	}

	@GetMapping("/update/{id}")
	public String preUpdate(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("position", positionService.findById(id));

		return "position/create";
	}

	@PostMapping("/update")
	public String update(@Valid Position position, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "position/create";
		}
		positionService.update(position);

		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");

		return "redirect:/position/create";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (positionService.positionHasEmployee(id)) {
			attr.addFlashAttribute("fail", "Cargo não excluido. Tem funcionário(s) vinculado(s).");
		} else {
			positionService.delete(id);
			attr.addFlashAttribute("success", "Cargo excluido com sucesso.");
		}
		return "redirect:/position/list";
	}

	@ModelAttribute("departments")
	public List<Department> departments() {
		return departmentService.listAll();
	}
}