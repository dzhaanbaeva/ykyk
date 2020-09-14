package com.ykyk.model;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public class PageableExample {
    public static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }
        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("users", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    public static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }
}
