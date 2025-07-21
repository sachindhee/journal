package net.engineeringdigest.journalApp.entity;

import lombok.*;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection  = "journal_entries")
@Data
@NoArgsConstructor
public class journalEntity {

    @Id
    private ObjectId id;
    @NonNull
    private String tital;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;


}
