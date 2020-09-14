package com.ykyk.controller;


import com.ykyk.model.Document;
import com.ykyk.model.PageableExample;
import com.ykyk.model.TypeOfDocument;
import com.ykyk.model.User;
import com.ykyk.repository.TypeOfDocumentRepository;
import com.ykyk.repository.UserRepository;
import com.ykyk.service.PropertiesService;
import com.ykyk.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping
@AllArgsConstructor
public class FrontendController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PropertiesService propService;
    private final TypeOfDocumentRepository typeOfDocumentRepository;



    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAnyAuthority('USER')")
    @GetMapping("/")
    public String mainPage(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        model.addAttribute("roles", userRepository.findByEmail(principal.getName()).getRoles().toString());
        return "index";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String seePeople(Model model, Principal principal, Pageable pageable, HttpServletRequest uriBuilder) {
        var users = userService.findUsers(pageable);
        var uri = uriBuilder.getRequestURI();
        var userModel = model.addAttribute("users", userService.findAllUsers());

        PageableExample.constructPageable(users, propService.getDefaultPageSize(), userModel, uri);
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        model.addAttribute("roles", userRepository.findByEmail(principal.getName()).getRoles().toString());
        return "users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add_user")
    public String addUser(Model model, Principal principal) {
        model.addAttribute("documents", typeOfDocumentRepository.findAll());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        model.addAttribute("roles", userRepository.findByEmail(principal.getName()).getRoles().toString());
        return "add_users";
    }

    @PostMapping("/add_user")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("fullName") String fullName, @RequestParam("inn") String inn,
                          @RequestParam("birthday") String birthday,
                          @RequestParam("address") String address, @RequestParam("email") String email,
                          @RequestParam("numberOfDoc") int numberOfDoc,
                          @RequestParam("issuedByWhom") String issuedByWhom, @RequestParam("dateOfIssue") String dateOfIssue,
                          @RequestParam("expirationDate") String expirationDate,
                          @RequestParam(required = false, name = "document") int documentId,
                          Model model, Principal principal) {
        model.addAttribute("error", "Гражданин с таким ИНН уже существует!");
        model.addAttribute("errors", "Гражданин с такой почтой уже существует!");
        String result = userService.createUser(name, fullName, inn, birthday, address, email,
                documentId, numberOfDoc, issuedByWhom, dateOfIssue, expirationDate);


        return result;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{id}")
    public String updateUser(@PathVariable("id") int id, Model model, Principal principal) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("document", typeOfDocumentRepository.findAll());
        model.addAttribute("users", userRepository.findByEmail(principal.getName()));
        model.addAttribute("roles", userRepository.findByEmail(principal.getName()).getRoles().toString());
        return "single_user";
    }


    @PostMapping("/update")
    public String updateUser(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("fullName") String fullName,
            @RequestParam("inn") String inn,
            @RequestParam("birthday") String birthday,
            @RequestParam("address") String address, @RequestParam("email") String email,
            @RequestParam(required = false, name = "document") int documentId,
            Model model, Principal principal
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthdayDate = birthday;
        LocalDate localDate = LocalDate.parse(birthdayDate, formatter);
        User user = userRepository.findById(id);
        TypeOfDocument typeDocument = typeOfDocumentRepository.findById(documentId);
        Document doc = user.getDocument();
        doc.setTypeOfDocument(typeDocument);
        user.setName(name);
        user.setFullName(fullName);
        user.setInn(inn);
        user.setBirthday(localDate);
        user.setAddress(address);
        user.setEmail(email);
        userRepository.save(user);

        return "redirect:/users";
    }


    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteById(userId);

        return "";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/search/{search}")
    public String search(@PathVariable("search") String search, Principal principal, Model model, HttpServletRequest uriBuilder, Pageable pageable) {
        String value = search;

        try {
           int data = Integer.parseInt(value);
            var user = userService.getUserNumber(data, pageable);
            var uri = uriBuilder.getRequestURI();
            var searchModel = model.addAttribute("users", user.getContent());
            PageableExample.constructPageable(user, propService.getDefaultPageSize(), searchModel, uri);
            model.addAttribute("user", userRepository.findByEmail(principal.getName()));
            model.addAttribute("roles", userRepository.findByEmail(principal.getName()).getRoles().toString());
        }catch (NumberFormatException e)
        {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(value, formatter);
                var userDate = userService.getUserBirthdayDate(localDate, pageable);
                var uri = uriBuilder.getRequestURI();
                var searchModel = model.addAttribute("users", userDate.getContent());
                PageableExample.constructPageable(userDate, propService.getDefaultPageSize(), searchModel, uri);
                model.addAttribute("user", userRepository.findByEmail(principal.getName()));
                model.addAttribute("roles", userRepository.findByEmail(principal.getName()).getRoles().toString());
            }catch (Exception n){

                var users = userService.getUserSearch(search, pageable);
                var uri = uriBuilder.getRequestURI();
                var searchModel = model.addAttribute("users", userService.getUserSearch(search, pageable).getContent());
                PageableExample.constructPageable(users, propService.getDefaultPageSize(), searchModel, uri);
                model.addAttribute("user", userRepository.findByEmail(principal.getName()));
                model.addAttribute("roles", userRepository.findByEmail(principal.getName()).getRoles().toString());
            }

        }

        return "users";
    }

}
