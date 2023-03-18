package nopainnogain.mailservice.core.dto;

public record MailDto(String to,
                      String subject,
                      String text) {
}
