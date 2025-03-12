package dev.jonkursani.restapigr1.dtos.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private String author;
//    private LocalDateTime createdAt;
//    private long createdBy;
}


