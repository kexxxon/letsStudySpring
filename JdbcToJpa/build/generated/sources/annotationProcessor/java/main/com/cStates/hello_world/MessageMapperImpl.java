package com.cStates.hello_world;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-30T13:23:30+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message messageDtoToMessage(MessagePostDto messagePostDto) {
        if ( messagePostDto == null ) {
            return null;
        }

        Message message = new Message();

        message.setMessage( messagePostDto.getMessage() );

        return message;
    }

    @Override
    public MessageResponseDto messageToMessageResponseDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageResponseDto messageResponseDto = new MessageResponseDto();

        messageResponseDto.setMessageId( message.getMessageId() );
        messageResponseDto.setMessage( message.getMessage() );

        return messageResponseDto;
    }
}
