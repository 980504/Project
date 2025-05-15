package project.chatbotApp.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.chatbotApp.service.ChatAiService;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private ChatAiService chatAiService;
    public ChatController(ChatAiService chatAiService){
        this.chatAiService = chatAiService;
    }

    @GetMapping(value = "/ask", produces = MediaType.TEXT_PLAIN_VALUE) //Garder la forme de la réponse pour quelle soit claire.
    public String ask(String question){
        return chatAiService.ragChat(question);
    }

}
