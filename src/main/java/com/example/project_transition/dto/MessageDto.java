package com.example.project_transition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {


    private Long id;
    private UserDto sender;
    private InboxesDto inbox;
    private String message;
    private Date createDate;


    public static final class MessageDtoBuilder {
        private Long id;
        private UserDto sender;
        private InboxesDto inbox;
        private String message;
        private Date createDate;

        private MessageDtoBuilder() {
        }

        public static MessageDtoBuilder aMessageDto() {
            return new MessageDtoBuilder();
        }

        public MessageDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public MessageDtoBuilder withSender(UserDto sender) {
            this.sender = sender;
            return this;
        }

        public MessageDtoBuilder withInbox(InboxesDto inbox) {
            this.inbox = inbox;
            return this;
        }

        public MessageDtoBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public MessageDtoBuilder withCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public MessageDto build() {
            MessageDto messageDto = new MessageDto();
            messageDto.setId(id);
            messageDto.setSender(sender);
            messageDto.setInbox(inbox);
            messageDto.setMessage(message);
            messageDto.setCreateDate(createDate);
            return messageDto;
        }
    }
}
