package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.MessageResponse;
import com.fedag.recruitmentSystem.service.impl.MessageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/messages")
@Tag(name = "Контроллер сообщений", description = "Работа с сообщениями")
public class MessageController {

  @Schema(name = "Сервис сообщений", description = "Содержит имплементацию методов для работы с репозиторием")
  private final MessageServiceImpl messageService;

  @Operation(summary = "Получение списка сообщений")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<MessageResponse> getAllMessage(@PageableDefault(size = 5) Pageable pageable) {
    return messageService.getAllMessages(pageable);
  }

  @Operation(summary = "Получение сообщения по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Сообщение загружено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public MessageResponse getMessageById(@PathVariable Long id) {
    return messageService.findById(id);
  }

  @Operation(summary = "Добавление сообщения")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Сообщение добавлено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addMessage(@RequestBody MessageResponse messageResponse) {
    messageService.save(messageResponse);
  }

  @Operation(summary = "Изменение сообщения")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Сообщение изменено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping
  public void updateMessage(@RequestBody MessageResponse messageResponse) {
    messageService.save(messageResponse);
  }

  @Operation(summary = "Удаление сообщения")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Сообщение удалено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void deleteMessage(@PathVariable Long id) {
    messageService.deleteById(id);
  }
}
