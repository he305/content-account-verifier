package com.github.he305.contentaccountverifier.application.web.controller;

import com.github.he305.contentaccountverifier.application.web.dto.ContentAccountDto;
import com.github.he305.contentaccountverifier.domain.service.ContentAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private final ContentAccountService contentAccountService;

    @PostMapping(value = "/verify")
    public void verify(@RequestBody ContentAccountDto dto) {
        contentAccountService.verify(dto.getContentAccountName(), dto.getPlatform());
    }
}
