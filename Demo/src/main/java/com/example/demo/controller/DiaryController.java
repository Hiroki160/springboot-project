package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Diary;
import com.example.demo.repository.DiaryRepository;

@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository repo;

    // 一覧表示
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("entries", repo.findAll());
        return "index";
    }

    // 投稿追加
    @PostMapping("/add")
    public String add(@RequestParam String title, @RequestParam String content) {
        Diary d = new Diary();
        d.setTitle(title);
        d.setContent(content);
        repo.save(d);
        return "redirect:/";
    }

    // 削除機能（オプション）
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}
