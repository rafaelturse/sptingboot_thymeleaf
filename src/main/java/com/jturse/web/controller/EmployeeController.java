package com.jturse.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jturse.domain.Employee;
import com.jturse.domain.Position;
import com.jturse.domain.enums.UF;
import com.jturse.service.EmployeeService;
import com.jturse.service.PositionService;
import com.jturse.web.validator.EmployeeValidator;

@Controller
@RequestMapping("/funcionarios")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PositionService positionService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new EmployeeValidator());
	}

	@GetMapping("/create")
	public String create(Employee employee) {
		return "employee/create";
	}

	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("employees", employeeService.listAll());
		return "employee/list";
	}

	@PostMapping("/save")
	public String save(@Valid Employee employee, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "employee/create";
		}

		employeeService.save(employee);

		attr.addFlashAttribute("success", "Funcionário inserido com sucesso.");

		return "redirect:/employees/create";
	}

	@GetMapping("/update/{id}")
	public String preUpdate(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("employee", employeeService.findById(id));
		return "employee/create";
	}

	@PostMapping("/update")
	public String editar(@Valid Employee employee, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "employee/create";
		}

		employeeService.update(employee);

		attr.addFlashAttribute("success", "Funcionário editado com sucesso.");

		return "redirect:/employees/create";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		employeeService.delete(id);

		attr.addFlashAttribute("success", "Funcionário removido com sucesso.");
		return "redirect:/employees/list";
	}

	@GetMapping("/list/name")
	public String listByName(@RequestParam("name") String name, ModelMap model) {
		model.addAttribute("employees", employeeService.listByName(name));

		return "employee/list";
	}

	@GetMapping("/list/position")
	public String listByPosition(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("employees", employeeService.listByPosition(id));

		return "employee/list";
	}

	@GetMapping("/list/date")
	public String listByDates(@RequestParam("entry") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entry,
			@RequestParam("exit") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate exit, ModelMap model) {
		model.addAttribute("employees", employeeService.listByEntryAndExit(entry, exit));
		return "employee/list";
	}

	@ModelAttribute("postions")
	public List<Position> getPositions() {
		return positionService.listAll();
	}

	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
}