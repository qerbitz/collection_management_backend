package com.example.project_transition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboxesDto {

    private Long id;
    private UserDto owner;
    private UserDto sender;
    private String lastMsg;


    public static final class InboxesDtoBuilder {
        private Long id;
        private UserDto owner;
        private UserDto sender;
        private String lastMsg;

        private InboxesDtoBuilder() {
        }

        public static InboxesDtoBuilder anInboxesDto() {
            return new InboxesDtoBuilder();
        }

        public InboxesDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public InboxesDtoBuilder withOwner(UserDto owner) {
            this.owner = owner;
            return this;
        }

        public InboxesDtoBuilder withSender(UserDto sender) {
            this.sender = sender;
            return this;
        }

        public InboxesDtoBuilder withLastMsg(String lastMsg) {
            this.lastMsg = lastMsg;
            return this;
        }

        public InboxesDto build() {
            InboxesDto inboxesDto = new InboxesDto();
            inboxesDto.setId(id);
            inboxesDto.setOwner(owner);
            inboxesDto.setSender(sender);
            inboxesDto.setLastMsg(lastMsg);
            return inboxesDto;
        }
    }
}
